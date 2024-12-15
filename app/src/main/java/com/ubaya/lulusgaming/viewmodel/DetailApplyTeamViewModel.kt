package com.ubaya.lulusgaming.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.lulusgaming.model.ApplyTeam
import com.ubaya.lulusgaming.model.ApplyTeamDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailApplyTeamViewModel(application: Application):
    AndroidViewModel(application),CoroutineScope{

    private val job = Job()

    val applyTeamLD = MutableLiveData<ApplyTeam>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addApplyTeam(list: List<ApplyTeam>){
        launch {
            val db = ApplyTeamDatabase.buildDatabase(getApplication())
            db.applyTeamDao().insertAll(*list.toTypedArray())
        }
    }


}