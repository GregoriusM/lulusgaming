package com.ubaya.lulusgaming.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.lulusgaming.model.Schedule

class ScheduleDetailViewModel:ViewModel() {
    val scheduleLD = MutableLiveData<Schedule>()

    fun fetch(schedule: Schedule){
        scheduleLD.value = schedule
    }
}