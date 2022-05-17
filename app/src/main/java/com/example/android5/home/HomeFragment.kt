package com.example.android5.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android5.database.FeedData
import com.example.android5.databinding.FragmentHomeBinding
import com.example.android5.model.Feed


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeVM
    private lateinit var adapter : FeedAdapter
    lateinit var newRecyclerView: RecyclerView
    lateinit var newArrayList: ArrayList<Feed>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(
            this,
            ViewModelFactory(context?.applicationContext as FeedData)
        )[HomeVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = FeedAdapter()
        val lm = LinearLayoutManager(this@HomeFragment.requireContext())
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