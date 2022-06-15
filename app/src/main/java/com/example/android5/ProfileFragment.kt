package com.example.android5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android5.databinding.FragmentProfileBinding
import com.example.android5.model.SQLiteHelper


class ProfileFragment : Fragment() {
    private lateinit var sqliteHelper: SQLiteHelper
    lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SQLiteHelper(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val bundle=arguments
        val message= bundle!!.getString("mssv")
        binding.mssv.text=message


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.mssv.text =  requireArguments().getString("mssv")
        var db = sqliteHelper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM TBL_STUDENT WHERE EMAIL ="+binding.mssv.text,null)
        if(rs.moveToNext()) {
            binding.studentName.text = rs.getString(1)
        }

        binding.btnlogout.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }


}