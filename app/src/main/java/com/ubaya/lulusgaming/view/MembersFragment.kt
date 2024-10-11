package com.ubaya.lulusgaming.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentMembersBinding


class MembersFragment : Fragment() {
    private lateinit var binding: FragmentMembersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMembersBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(R.drawable.valorant)
            .into(binding.imageViewTeams)

        binding.txtTeamNameMembers.text = "TEAM A"

        binding.playerName1.text = "Captain America"
        binding.playerRole1.text = "Role: Duelist"

        Picasso.get()
            .load(R.drawable.capt)
            .into(binding.imageViewAvatar1)

        binding.playerName2.text = "Ironman"
        binding.playerRole2.text = "Role: Controller"

        Picasso.get()
            .load(R.drawable.ironman)
            .into(binding.imageViewAvatar2)

        binding.playerName3.text = "Dr. Strange"
        binding.playerRole3.text = "Role: Sentinel"

        Picasso.get()
            .load(R.drawable.strange)
            .into(binding.imageViewAvatar3)
    }


}