package com.ubaya.lulusgaming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentAchievementBinding
import com.ubaya.lulusgaming.model.Game
import com.ubaya.lulusgaming.viewmodel.GameListViewModel


class AchievementFragment : Fragment() {
    private lateinit var binding: FragmentAchievementBinding
    private val achievementListAdapter = AchievementListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(layoutInflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_achievement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = AchievementFragmentArgs.fromBundle(requireArguments())
        val selectedGame: Game = args.selectedGame

        binding.recyclerViewAchievements.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewAchievements.adapter = achievementListAdapter

        binding.txtGameAchievements.text = selectedGame.name

        Picasso.get()
            .load(selectedGame.photoUrl)
            .into(binding.imageViewAchievements)

        achievementListAdapter.updateAchievementList(selectedGame.achievements)

        val years = selectedGame.achievements.mapNotNull { it.year?.toIntOrNull() }.distinct().sorted()
        val allYearsOption = listOf("All") + years.map { it.toString() }

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, allYearsOption)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = spinnerAdapter

        binding.progresLoadAchievement.visibility = View.GONE

        binding.refreshLayout.setOnRefreshListener {
            val selectedYear = binding.spinnerYear.selectedItem as String
            if(selectedYear == "All"){
                achievementListAdapter.updateAchievementList(selectedGame.achievements)
            } else{
                val filteredAchievements = selectedGame.achievements.filter { it.year == selectedYear }
                achievementListAdapter.updateAchievementList(ArrayList(filteredAchievements))
            }

            binding.refreshLayout.isRefreshing = false
        }
        binding.spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedYear = parent?.getItemAtPosition(position) as String
                if(selectedYear == "All"){
                    achievementListAdapter.updateAchievementList(selectedGame.achievements)
                }
                else{
                val filteredAchivements = selectedGame.achievements.filter { it.year == selectedYear }
                achievementListAdapter.updateAchievementList(ArrayList(filteredAchivements))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

}