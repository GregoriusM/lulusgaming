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
import com.ubaya.lulusgaming.model.Game

class GameListViewModel(application: Application):AndroidViewModel(application) {
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh() {
        gameLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://www.jsonkeeper.com/b/2HV0" (tidak jadi dipakai)
//        val url = "https://www.jsonkeeper.com/b/W7GP" (tidak jadi dipakai)
        val url = "https://www.jsonkeeper.com/b/J2Y9"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                val sType = object: TypeToken<List<Game>>() {}.type
                val result = Gson().fromJson<List<Game>>(it, sType)
                gamesLD.value = result as ArrayList<Game>
            },
            {
                gameLoadErrorLD.value = true
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