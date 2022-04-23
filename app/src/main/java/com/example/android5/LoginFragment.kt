package com.example.android5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android5.databinding.FragmentSigninBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        binding.register.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.toSignup.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.button.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_loginFragment_to_mainActivity2)
        }
    }



}