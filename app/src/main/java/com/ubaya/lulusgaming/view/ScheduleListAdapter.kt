package com.ubaya.lulusgaming.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.lulusgaming.databinding.ScheduleListItemBinding
import com.ubaya.lulusgaming.model.Schedule

class ScheduleListAdapter (val scheduleList:ArrayList<Schedule>):
    RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>()
{
        class ScheduleViewHolder(var binding:ScheduleListItemBinding):
                RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.binding.txtDate.text = scheduleList[position].datetime
        holder.binding.txtEvent.text =scheduleList[position].eventName
        holder.binding.txtGameName.text = scheduleList[position].gameName
        holder.binding.txtTeam.text = scheduleList[position].team

//        val name = studentList[position].name //kasus ini kalo ada argumen di navigation. NANTI DIGANTI PAKE INI KALO UDAH NAMBAH ARG

        holder.binding.txtEvent.setOnClickListener{
            val action = ScheduleListFragmentDirections.actionScheduleDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateScheduleList(newScheduleList: ArrayList<Schedule>){
        scheduleList.clear()
        scheduleList.addAll(newScheduleList)
        notifyDataSetChanged() //untuk memberitahu data telah ter updated
    }

}