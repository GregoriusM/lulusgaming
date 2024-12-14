package com.ubaya.lulusgaming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentApplyTeamBinding
import com.ubaya.lulusgaming.model.ApplyTeam
import com.ubaya.lulusgaming.viewmodel.ApplyTeamViewModel


class ApplyTeamFragment : Fragment() {
    private lateinit var binding: FragmentApplyTeamBinding
    private lateinit var viewModel: ApplyTeamViewModel
    private val applyListAdapter = ApplyListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_apply_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ApplyTeamViewModel::class.java)
        viewModel.refresh()
        binding.recProposal.layoutManager = LinearLayoutManager(context)
        binding.recProposal.adapter = applyListAdapter

        binding.btnFab.setOnClickListener{
            val action = ApplyTeamFragmentDirections.actionItemApplyTeamToAddTeamFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.applyLD.observe(viewLifecycleOwner, Observer {
            applyListAdapter.updateApplyTeamList(it)
            if(it.isEmpty()) {
                binding.txtError.setText("Your Proposal List still empty.")
                binding.recProposal?.visibility = View.GONE
            } else {
                binding.recProposal?.visibility = View.VISIBLE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false){
                binding.progressLoad?.visibility = View.GONE
            } else{
                binding.progressLoad?.visibility = View.VISIBLE
            }
        })

        viewModel.applyLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false){
                binding.txtError?.visibility = View.GONE
            } else{
                binding.txtError?.visibility = View.VISIBLE
            }
        })


    }

}