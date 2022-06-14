package com.example.android5

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android5.databinding.FragmentSigninBinding
import com.example.android5.model.InputValidation
import com.example.android5.model.SQLiteHelper
import com.example.android5.newstatus.NewFragment
import com.example.fooddelivery.LoginVM
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private lateinit var sqliteHelper: SQLiteHelper
    lateinit var binding: FragmentSigninBinding
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var  viewModel: LoginVM
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var inputValidation: InputValidation
    lateinit var sharerf: MySharedpreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sqliteHelper = SQLiteHelper(this)
        viewModel = ViewModelProvider(this).get(LoginVM::class.java)
        MySharedpreferences.init(requireContext())
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
            val email = binding.userName.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if(loginStudent(email,password))
            {
                val controller = findNavController()
                val bundle = Bundle()
                val intent = Intent(this.requireContext(), MainActivity::class.java)
                bundle.putString("mssv",email)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
                //controller.navigate(R.id.action_loginFragment_to_mainActivity2)
                MySharedpreferences.saveUsername(email)

            }
           // verifyFromSQLite()
        }
        viewModel.isSuccessEvent.observe(this.viewLifecycleOwner) { isSucess ->
            if (isSucess) {
                val controller = findNavController()
                //controller.navigate(R.id.action_loginFragment_to_mainActivity2)
            }
        }
        viewModel.isErrorEvent.observe(this.viewLifecycleOwner) { errMsg ->
            val builder = AlertDialog.Builder(this.requireContext())
            builder.apply {
                setTitle("Android Alert")
                setMessage(errMsg)
                setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface: DialogInterface?, id: Int ->
                })
            }
            builder.show()
        }

    }
    private fun loginStudent(email: String,password:String): Boolean {
        viewModel.checkEmailAndPassword(email,password)
        var db = sqliteHelper.readableDatabase
        var args = listOf<String>(binding.userName.text.toString(),binding.password.text.toString()).toTypedArray()
        var rs = db.rawQuery("SELECT * FROM TBL_STUDENT WHERE EMAIL = ? AND PASS = ?",args)
        if (rs.moveToNext()) {
            Toast.makeText(this.requireContext(), "Welcome to Access", Toast.LENGTH_LONG).show()
            return true
        }
        else{
            Toast.makeText(this.requireContext(), "Invalid Credential", Toast.LENGTH_LONG).show()
            return false
        }
    }
    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!, textInputLayoutPassword!!, getString(R.string.error_message_email))) {
            return
        }
        if (sqliteHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' }, textInputEditTextPassword!!.text.toString().trim { it <= ' ' })) {
            val accountsIntent = Intent(activity, NewFragment::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView!!, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }
}

