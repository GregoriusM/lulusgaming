package com.ubaya.lulusgaming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentScheduleListBinding
import com.ubaya.lulusgaming.viewmodel.ScheduleListViewModel

class ScheduleListFragment : Fragment() {
    private lateinit var binding: FragmentScheduleListBinding
    private lateinit var viewModel: ScheduleListViewModel
    private val scheduleListAdapter = ScheduleListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleListBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
        //        return inflater.inflate(R.layout.fragment_schedule_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleListViewModel::class.java)
        viewModel.refresh()

        binding.recViewSchedule.layoutManager = LinearLayoutManager(context)
        binding.recViewSchedule.adapter = scheduleListAdapter

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recViewSchedule.visibility = View.GONE
            binding.txtErrorSchedule.visibility = View.GONE
            binding.progressLoadSchedule.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.scheduleLD.observe(viewLifecycleOwner, Observer {
            scheduleListAdapter.updateScheduleList(it)
        })

        viewModel.scheduleLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtErrorSchedule?.visibility = View.VISIBLE
            }else{
                binding.txtErrorSchedule?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.recViewSchedule.visibility = View.GONE
                binding.progressLoadSchedule.visibility = View.VISIBLE
            }else{
                binding.recViewSchedule.visibility = View.VISIBLE
                binding.progressLoadSchedule.visibility = View.GONE
            }
        })
    }



}