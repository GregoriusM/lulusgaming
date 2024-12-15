package com.ubaya.lulusgaming.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentAddTeamBinding
import com.ubaya.lulusgaming.model.ApplyTeam
import com.ubaya.lulusgaming.viewmodel.DetailApplyTeamViewModel

class AddTeamFragment : Fragment(),  ApplyEditClickListener{
    private lateinit var binding:FragmentAddTeamBinding
    private lateinit var viewmodel: DetailApplyTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_team, container, false)
        binding = FragmentAddTeamBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this)
            .get(DetailApplyTeamViewModel::class.java)

        binding.applyTeam = ApplyTeam("", "", "", "")
        binding.addlistener = this



//        binding.btnApply.setOnClickListener {
//            var apply = ApplyTeam(
//                binding.txtDescription.text.toString()
//            )
//        }
    }

    override fun onEditClick(v: View) {
        var applyTeam = ApplyTeam(
            "Nama Game",
            "Team A",
            "ACCEPT",
            binding.txtDescription.text.toString()
        )

        val list = listOf(applyTeam)

        viewmodel.addApplyTeam(list)

        Toast.makeText(v.context, "Data added",
            Toast.LENGTH_LONG).show()

        Navigation.findNavController(v).popBackStack()
    }

}