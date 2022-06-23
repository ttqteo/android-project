package com.example.android5.signup

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android5.MySharedpreferences
import com.example.android5.R
import com.example.android5.databinding.FragmentSignupBinding
import com.example.android5.model.SQLiteHelper
import com.example.android5.model.StudentModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignupFragment : Fragment() {


    private lateinit var sqliteHelper:SQLiteHelper
    private lateinit var signupVM: SignupVM
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
            if(addStudent()) {
                val controller = findNavController()
                controller.navigate(R.id.action_signupFragment_to_loginFragment)
            }
            else
            {
                Toast.makeText( this@SignupFragment.requireContext(),"Sign up Fail",Toast.LENGTH_SHORT).show()
            }
        }

        binding.login.setOnClickListener {
            getStudent()
            val controller = findNavController()
            controller.navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
    private fun getStudent(){
        val stdList =sqliteHelper.getAllStudent()
        //Log.e("pppp","${stdList.size}")
    }
    private fun addStudent(): Boolean {
        val name = binding.name.text.toString()
        val email = binding.userName.text.toString()
        val pass = binding.password.text.toString()

        if (name.isEmpty() ||email.isEmpty())
        {
            Toast.makeText( this@SignupFragment.requireContext(),"Please enter name & email",Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            if (checkemail(email))
            {  addusers(name,email,pass)
                val std = StudentModel( name = name, email = email, pass = pass)
                val status  = sqliteHelper.insertStudent(std)
                if (status > -1 )
                {
                    Toast.makeText( this@SignupFragment.requireContext(),"Student add",Toast.LENGTH_SHORT).show()
                    return true
                    //clearEditText()
                }
                else
                    return false
            }

            else{
                Toast.makeText( this@SignupFragment.requireContext(),"Record not save",Toast.LENGTH_SHORT).show()
                return false
            }
        }
    }

    private fun clearEditText(){
        binding.name.setText("")
        binding.userName.setText("")
        binding.name.requestFocus()
    }
    fun checkemail(mail:String): Boolean
    {
        val db = Firebase.firestore
        var docRef = db.collection("users").document(mail);

        docRef.get().addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                val document = task.result
                if(document != null) {
                    if (document.exists()) {
                        //Log.d("TAG", "Document already exists.")
                    MySharedpreferences.savemes("exists")
                    } else {
                        MySharedpreferences.savemes("")
                        //Log.d("TAG", "Document doesn't exist.")
                    }
                }
            } else {
                //Log.d("TAG", "Error: ", task.exception)
            }
        }
        val mes=MySharedpreferences.getmes()
        if (mes=="exists") {
            Toast.makeText( this@SignupFragment.requireContext(),"email have been exists ",Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }
    fun addusers(name: String,  email: String, password: String){
        val db = Firebase.firestore
        val user = hashMapOf(
            "name" to name,
            "password" to password
        )
        db.collection("users").document(email)
            .set(user)
            .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error writing document", e) }
    }

}