package com.ubaya.lulusgaming.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.lulusgaming.databinding.ApplyteamItemLayoutBinding
import com.ubaya.lulusgaming.databinding.FragmentAddTeamBinding
import com.ubaya.lulusgaming.model.ApplyTeam

class ApplyListAdapter(
    val applyTeamList:ArrayList<ApplyTeam>)
    : RecyclerView.Adapter<ApplyListAdapter.ApplyTeamViewHolder>(){

    class ApplyTeamViewHolder(var binding: ApplyteamItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplyTeamViewHolder {
        val binding = ApplyteamItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ApplyTeamViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ApplyListAdapter.ApplyTeamViewHolder, position: Int) {
        holder.binding.applyTeam = applyTeamList[position]
    }

    override fun getItemCount(): Int {
        return applyTeamList.size
    }

    fun updateApplyTeamList(newApplyTeamList: List<ApplyTeam>){
        applyTeamList.clear()
        applyTeamList.addAll(newApplyTeamList)
        notifyDataSetChanged()
    }



}