package com.ubaya.lulusgaming.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.lulusgaming.model.ApplyTeam
import com.ubaya.lulusgaming.model.ApplyTeamDatabase
import com.ubaya.lulusgaming.util.buildApplyDb
import com.ubaya.lulusgaming.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ApplyTeamViewModel(application: Application):
    AndroidViewModel(application), CoroutineScope{

    private var job = Job()

    val applyLD = MutableLiveData<List<ApplyTeam>>()
    val applyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value = true
        applyLoadErrorLD.value = false
        launch {
            val db = buildApplyDb(getApplication())
            applyLD.postValue(db.applyTeamDao().selectAllApplyTeam())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(applyTeam: ApplyTeam){
        launch {
            val db = buildApplyDb(getApplication())
            db.applyTeamDao().deleteApplyTeam(applyTeam)
            applyLD.postValue(db.applyTeamDao().selectAllApplyTeam())
        }
    }

}