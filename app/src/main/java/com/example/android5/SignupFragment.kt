package com.example.android5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android5.databinding.FragmentSignupBinding
import com.example.android5.model.SQLiteHelper
import com.example.android5.model.StudentModel


class SignupFragment : Fragment() {


    private lateinit var sqliteHelper:SQLiteHelper
    lateinit var binding: FragmentSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sqliteHelper = SQLiteHelper(this)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignup.setOnClickListener {
            addStudent()
            val controller = findNavController()
            controller.navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.login.setOnClickListener {
            getStudent()
            val controller = findNavController()
            //controller.navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
    private fun getStudent(){
        val stdList =sqliteHelper.getAllStudent()
        Log.e("pppp","${stdList.size}")
    }
    private fun addStudent(){
        val name = binding.name.text.toString()
        val email = binding.userName.text.toString()
        val pass = binding.password.text.toString()

        if (name.isEmpty() ||email.isEmpty())
        {
            Toast.makeText( this@SignupFragment.requireContext(),"Please enter nam email",Toast.LENGTH_SHORT).show()
        }
        else {
            val std = StudentModel(name = name, email = email, pass = pass)
            val status  = sqliteHelper.insertStudent(std)
            if (status > -1 )
            {
                Toast.makeText( this@SignupFragment.requireContext(),"Student add",Toast.LENGTH_SHORT).show()
                clearEditText()
            }
            else{
                Toast.makeText( this@SignupFragment.requireContext(),"Record not save",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText(){
        binding.name.setText("")
        binding.userName.setText("")
        binding.name.requestFocus()
    }
}