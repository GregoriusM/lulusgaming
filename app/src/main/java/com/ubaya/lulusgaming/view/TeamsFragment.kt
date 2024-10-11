package com.ubaya.lulusgaming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentTeamsBinding

class TeamsFragment : Fragment() {
    private lateinit var binding: FragmentTeamsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamsBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTeamA.text = "TEAM A"
        binding.btnTeamB.text = "TEAM B"
        binding.btnTeamC.text = "TEAM C"

        binding.btnTeamA.setOnClickListener{
            val action = TeamsFragmentDirections.actionMembersFragment()
            it.findNavController().navigate(action)
        }


    }


}