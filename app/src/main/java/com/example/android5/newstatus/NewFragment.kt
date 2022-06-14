package com.example.android5.newstatus

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android5.MySharedpreferences
import com.example.android5.database.FeedData
import com.example.android5.databinding.FragmentNewBinding
import com.example.android5.model.SQLiteHelper
import java.util.*


class NewFragment : Fragment() {
    lateinit var viewModel:NewStatusVM
    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var binding: FragmentNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(
            this,
            ViewModelFactory(context?.applicationContext as FeedData)
        )[NewStatusVM::class.java]
        sqliteHelper = SQLiteHelper(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btPush.setOnClickListener{
            var name=MySharedpreferences.getUsername()
            if (name==null) name="*"
            val status = binding.statusNew.text.toString().trim()
            val title_status = binding.statusNew4.text.toString().trim()
            val link_home_web = binding.linkstatus.text.toString().trim()
            viewModel.addFeed(
                name ,
                currentDateandTime,
                status,
                title_status,
                link_home_web)
            Toast.makeText(this.requireContext(), "poster complete", Toast.LENGTH_LONG).show()

        }
    }

    var sdf: SimpleDateFormat = SimpleDateFormat("HH/mm_dd/MM/yyyy")
    var currentDateandTime: String = sdf.format(Date())

}


