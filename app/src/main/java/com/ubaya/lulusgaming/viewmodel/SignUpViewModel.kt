package com.ubaya.lulusgaming.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.lulusgaming.model.Account
import com.ubaya.lulusgaming.model.AccountDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SignUpViewModel(application: Application):
AndroidViewModel(application),CoroutineScope {

    private val job = Job()

    val accountLD = MutableLiveData<Account?>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addAccount(list: List<Account>){
        launch {
            val db = AccountDatabase.buildDatabase(getApplication())
            db.accountDao().insertAccount(*list.toTypedArray())
        }
    }

    fun verifyAccount(username: String, password: String) {
        launch {
            val db = AccountDatabase.buildDatabase(getApplication())
            val account = db.accountDao().selectAccount(username) // Query by username

            // Check if account exists and password matches
            if (account != null && account.password == password) {
                accountLD.postValue(account) // Post the account if verification succeeds
            } else {
                accountLD.postValue(null) // Post null if verification fails
            }
        }
    }

}