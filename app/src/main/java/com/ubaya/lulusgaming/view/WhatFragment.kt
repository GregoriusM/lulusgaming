package com.ubaya.lulusgaming.view

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentWhatBinding
import com.ubaya.lulusgaming.viewmodel.GameListViewModel

//ACC 2 DONE

class WhatFragment : Fragment() {
    private lateinit var binding: FragmentWhatBinding
    private lateinit var viewModel: GameListViewModel
    private val whatListAdapter = WhatListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatBinding.inflate(layoutInflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_what, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GameListViewModel::class.java)
        viewModel.refresh()

        binding.recViewWhat.layoutManager = LinearLayoutManager(context)
        binding.recViewWhat.adapter = whatListAdapter

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            whatListAdapter.updateWhatList(it)
            binding.refreshLayout.isRefreshing = false
        })

        viewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtErrorWhat?.visibility = View.VISIBLE
            } else {
                binding.txtErrorWhat?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.recViewWhat.visibility = View.GONE
                binding.progressLoadWhat.visibility = View.VISIBLE
            } else {
                binding.recViewWhat.visibility = View.VISIBLE
                binding.progressLoadWhat.visibility = View.GONE
            }
        })
    }


}