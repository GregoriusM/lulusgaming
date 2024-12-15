package com.ubaya.lulusgaming.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.lulusgaming.model.Achievement
import com.ubaya.lulusgaming.model.ApplyTeam
import com.ubaya.lulusgaming.model.ApplyTeamDatabase
import com.ubaya.lulusgaming.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailApplyTeamViewModel(application: Application):
    AndroidViewModel(application),CoroutineScope{

    private val job = Job()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    val gameList: MutableLiveData<List<Game>> = MutableLiveData()


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    init {
        queue = Volley.newRequestQueue(getApplication())
    }

    fun addApplyTeam(list: List<ApplyTeam>){
        launch {
            val db = ApplyTeamDatabase.buildDatabase(getApplication())
            db.applyTeamDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetchGames() {
        val url = "https://www.jsonkeeper.com/b/JA67"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val sType = object : TypeToken<List<Game>>() {}.type
                val result = Gson().fromJson<List<Game>>(response, sType)
                gameList.postValue(result)
            },
            { error ->
                error.printStackTrace()
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
        queue?.cancelAll(TAG)
    }




}