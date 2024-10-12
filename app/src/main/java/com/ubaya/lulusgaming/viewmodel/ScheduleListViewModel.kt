package com.ubaya.lulusgaming.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.lulusgaming.model.Schedule

class ScheduleListViewModel(application: Application):AndroidViewModel(application) {
    val scheduleLD = MutableLiveData<ArrayList<Schedule>>()
    val scheduleLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()


    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
        scheduleLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://www.jsonkeeper.com/b/IT4K" (tidak dipakai)
        val url = "https://www.jsonkeeper.com/b/LSJX"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                val sType = object: TypeToken<List<Schedule>>() {}.type
                val result = Gson().fromJson<List<Schedule>>(it, sType)
                scheduleLD.value = result as ArrayList<Schedule>
            },
            {
                scheduleLoadErrorLD.value = true
                loadingLD.value = false
            }

        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}