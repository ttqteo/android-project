package com.example.android5.notify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android5.database.FeedData
import com.example.android5.notify.ViewModelFactory
import com.example.android5.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    lateinit var viewModel: NotificationVM
    private lateinit var adapter : NotiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(
            this,
            ViewModelFactory(context?.applicationContext as FeedData)
        )[NotificationVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = NotiAdapter()
        val lm = LinearLayoutManager(this@NotificationFragment.requireContext())
        binding.recyclerView.layoutManager=lm
        binding.recyclerView.adapter=adapter
        registerData()

    }

    override fun onStart() {
        super.onStart()
        viewModel.loadData()
    }


    private  fun registerData() {
        viewModel.listOfData.observe(viewLifecycleOwner){ listOfRes ->
            adapter.submitList(listOfRes)
        }
    }

}