package com.ubaya.lulusgaming.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.lulusgaming.databinding.AchievementListItemBinding
import com.ubaya.lulusgaming.model.Achievement
import com.ubaya.lulusgaming.model.Game

class AchievementListAdapter(val achievementList:ArrayList<Achievement>):RecyclerView.Adapter<AchievementListAdapter.AchievementViewHolder>() {
    class AchievementViewHolder(val binding: AchievementListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val binding = AchievementListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return AchievementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
//        holder.binding.txtTournamentName.text = achievementList[position].tournament
//        holder.binding.txtYear.text = achievementList[position].year
//        holder.binding.txtTeam.text = achievementList[position].team

        val display = "${position + 1}. ${achievementList[position].tournament} (${achievementList[position].year}) - ${achievementList[position].team}"
        holder.binding.txtAchievementGame.text = display
    }

    override fun getItemCount(): Int {
        return achievementList.size
    }

    fun updateAchievementList(newAchievementList: ArrayList<Achievement>){
        achievementList.clear()
        achievementList.addAll(newAchievementList)
        notifyDataSetChanged()
    }
}