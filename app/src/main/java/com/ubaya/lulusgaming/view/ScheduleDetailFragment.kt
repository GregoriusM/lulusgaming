package com.ubaya.lulusgaming.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.ubaya.lulusgaming.databinding.FragmentScheduleDetailBinding
import com.ubaya.lulusgaming.model.Game
import com.ubaya.lulusgaming.model.Schedule
import com.ubaya.lulusgaming.viewmodel.ScheduleDetailViewModel
import com.ubaya.lulusgaming.viewmodel.ScheduleListViewModel

class ScheduleDetailFragment : Fragment() {
    private lateinit var binding: FragmentScheduleDetailBinding
    private lateinit var viewModel:ScheduleDetailViewModel

    private lateinit var schedule:Schedule


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleDetailBinding.inflate(layoutInflater, container,false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_schedule_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventName = arguments?.getString("eventName")
        val location = arguments?.getString("location")
        val time = arguments?.getString("time")
        val team = arguments?.getString("team")
        val eventDesc = arguments?.getString("eventDesc")
        val urlEvent = arguments?.getString("urlEvent")

        schedule = Schedule(null, null, eventName, null, team, location, time,eventDesc,urlEvent)

        viewModel = ViewModelProvider(this).get(ScheduleDetailViewModel::class.java)
        viewModel.fetch(schedule)

        observeViewModel()

        binding.btnNotify.setOnClickListener {
            showNotif()
        }



    }

    fun observeViewModel() {
        viewModel.scheduleLD.observe(viewLifecycleOwner, Observer {
            binding.txtNamaKegiatan.setText(it.eventName)
            binding.txtTempatWaktu.setText("${it.location} (${it.time})")
            binding.txtNamaTeamLomba.setText(it.team)
            binding.txtDeskripsiLomba.setText(it.eventDesc)

            Picasso.get()
                .load(it.urlEvent)
                .into(binding.imageVenue)
        })
    }

    fun showNotif() {
        val builder = AlertDialog.Builder(requireContext())
        val dialog = builder.setMessage("Notification created.")
            .create()

        dialog.show()

    }

}