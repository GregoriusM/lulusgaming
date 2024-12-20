package com.ubaya.lulusgaming.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.lulusgaming.databinding.WhatListItemBinding
import com.ubaya.lulusgaming.model.Game

class WhatListAdapter(val whatList: ArrayList<Game>):
    RecyclerView.Adapter<WhatListAdapter.GameViewHolder>(),
    ButtonClickListener {
    class GameViewHolder(val binding: WhatListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = WhatListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GameViewHolder(binding)

    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.game = whatList[position]

        holder.binding.listener = this
//        holder.binding.txtNamaGame.text = whatList[position].name
//        holder.binding.txtDeskripsiGame.text = whatList[position].gameDesc
//
//        Picasso.get()
//            .load(whatList[position].photoUrl)
//            .into(holder.binding.imageView)
//
//        holder.binding.btnAchievement.setOnClickListener {
//            val action = WhatFragmentDirections.actionAchievementFragment(whatList[position])
//            it.findNavController().navigate(action)
//        }
//
//        holder.binding.btnTeams.setOnClickListener {
//            val action = WhatFragmentDirections.actionTeamsFragment()
//            it.findNavController().navigate(action)
//        }


    }

    override fun getItemCount(): Int {
        return whatList.size
    }

    fun updateWhatList(newWhatList: ArrayList<Game>){
        whatList.clear()
        whatList.addAll(newWhatList)
        notifyDataSetChanged()
    }

    override fun onButtonClick(v: View){
        val name = v.tag.toString()

        for (game in whatList){
            if(game.name == name){
                val action = WhatFragmentDirections.actionAchievementFragment(game)
                Navigation.findNavController(v).navigate(action)
                break
            }

        }
    }

    override fun onTeamButtonClick(v: View) {
        val action = WhatFragmentDirections.actionTeamsFragment()
        Navigation.findNavController(v).navigate(action)
    }


}
