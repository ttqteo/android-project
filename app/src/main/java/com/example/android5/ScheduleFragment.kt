package com.example.android5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android5.databinding.FragmentHomeBinding
import com.example.android5.databinding.FragmentScheduleBinding
import com.example.android5.model.Feed
import com.example.android5.model.Schedule


class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    lateinit var viewModel: ScheduleVM
    private lateinit var adapter : ScheduleAdapter
    lateinit var newRecyclerView: RecyclerView
    lateinit var newArrayList: ArrayList<Schedule>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProvider(this).get(ScheduleVM::class.java)

        adapter = ScheduleAdapter()
        val lm = LinearLayoutManager(this@ScheduleFragment.requireContext())
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