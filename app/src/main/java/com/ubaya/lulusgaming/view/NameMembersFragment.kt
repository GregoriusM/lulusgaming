package com.ubaya.lulusgaming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentNameMembersBinding


class NameMembersFragment : Fragment() {
    private lateinit var binding: FragmentNameMembersBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNameMembersBinding.inflate(layoutInflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_name_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name")

        binding.txtNameMemberTeam.text = name
    }

}