package com.ubaya.lulusgaming.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.lulusgaming.databinding.ScheduleListItemBinding
import com.ubaya.lulusgaming.model.Schedule

//CHANGE TO DATA BINDING
class ScheduleListAdapter (val scheduleList:ArrayList<Schedule>): RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>() {
        class ScheduleViewHolder(var binding:ScheduleListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

        holder.binding.txtTanggal.text = scheduleList[position].date
        holder.binding.txtBulan.text = scheduleList[position].month
        holder.binding.txtKegiatan.text = scheduleList[position].eventName

        val cabangTeam = "${scheduleList[position].gameName} - ${scheduleList[position].team}"
        holder.binding.txtCabangTeam.text = cabangTeam

        val eventName = scheduleList[position].eventName
        val location = scheduleList[position].location
        val time = scheduleList[position].time
        val team = scheduleList[position].team
        val eventDesc = scheduleList[position].eventDesc
        val urlEvent = scheduleList[position].urlEvent

        holder.itemView.setOnClickListener {
            val action = ScheduleListFragmentDirections.actionItemScheduleToScheduleDetailFragment(eventName!!,location!!,time!!,team!!,eventDesc!!,urlEvent!!)
            it.findNavController().navigate(action)
        }

    }
    fun updateScheduleList(newScheduleList: ArrayList<Schedule>){
        scheduleList.clear()
        scheduleList.addAll(newScheduleList)
        notifyDataSetChanged()
    }

}