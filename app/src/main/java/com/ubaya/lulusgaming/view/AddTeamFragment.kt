package com.ubaya.lulusgaming.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.lulusgaming.databinding.FragmentAddTeamBinding
import com.ubaya.lulusgaming.model.ApplyTeam
import com.ubaya.lulusgaming.model.Game
import com.ubaya.lulusgaming.viewmodel.DetailApplyTeamViewModel

class AddTeamFragment : Fragment(), ApplyEditClickListener {
    private lateinit var binding: FragmentAddTeamBinding
    private lateinit var viewModel: DetailApplyTeamViewModel

    private lateinit var gameSpinner: Spinner
    private lateinit var teamSpinner: Spinner

    private var gameList: List<Game> = emptyList()
    private var teamList: List<String> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(DetailApplyTeamViewModel::class.java)

        binding.applyTeam = ApplyTeam("", "", "", "")
        binding.addlistener = this

        gameSpinner = binding.spinnerGame
        teamSpinner = binding.spinnerTeam

        viewModel.fetchGames()

        viewModel.gameList.observe(viewLifecycleOwner) { games ->
            gameList = games ?: emptyList()
            val gameNames = gameList.map { it.name ?: "" }
            val gameAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                gameNames
            )
            gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            gameSpinner.adapter = gameAdapter
        }

        gameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedGame = gameList.getOrNull(position)
                if (selectedGame != null) {
                    teamList = selectedGame.achievements.map { it.team ?: "" }.distinct()
                    val teamAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        teamList
                    )
                    teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    teamSpinner.adapter = teamAdapter
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                teamList = emptyList()
                val teamAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    teamList
                )
                teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                teamSpinner.adapter = teamAdapter
            }
        }
    }

    override fun onEditClick(v: View) {
        val status = listOf("WAITING", "DECLINED", "GRANTED")
        val randomStatus = status.random()

        val applyTeam = ApplyTeam(
            gameSpinner.selectedItem?.toString() ?: "",
            teamSpinner.selectedItem?.toString() ?: "",
            randomStatus,
            binding.txtDescription.text.toString()
        )

        val list = listOf(applyTeam)
        viewModel.addApplyTeam(list)

        Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }
}
