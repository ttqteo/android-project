package com.example.android5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android5.databinding.FragmentSigninBinding
import com.example.android5.model.SQLiteHelper


class LoginFragment : Fragment() {
    private lateinit var sqliteHelper: SQLiteHelper
    lateinit var binding: FragmentSigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SQLiteHelper(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.register.setOnClickListener {
//            val controller = findNavController()
//            controller.navigate(R.id.action_loginFragment_to_signupFragment)
//        }

        binding.signup.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.btnLogin.setOnClickListener {
            if(loginStudent()) {
                val controller = findNavController()
                controller.navigate(R.id.action_loginFragment_to_mainActivity2)
            }
        }
    }

    private fun loginStudent(): Boolean {
        var db = sqliteHelper.readableDatabase
        var args = listOf<String>(binding.userName.text.toString(),binding.password.text.toString()).toTypedArray()
        var rs = db.rawQuery("SELECT * FROM TBL_STUDENT WHERE NAME = ? AND PASS = ?",args)
        if (rs.moveToNext()) {
            Toast.makeText(this.requireContext(), "Welcome to Access", Toast.LENGTH_LONG).show()
            return true
        }
        else{
            Toast.makeText(this.requireContext(), "Invalid Credential", Toast.LENGTH_LONG).show()
            return false
        }
    }

}