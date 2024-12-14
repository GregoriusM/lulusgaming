package com.ubaya.lulusgaming.util

import android.content.Context
import com.ubaya.lulusgaming.model.AccountDatabase
import com.ubaya.lulusgaming.model.ApplyTeamDatabase

val DB_NAME = "lulusgamingdb"

fun buildDb(context: Context):AccountDatabase{
    val db = AccountDatabase.buildDatabase(context)
    return db
}

fun buildApplyDb(context: Context):ApplyTeamDatabase{
    val db = ApplyTeamDatabase.buildDatabase(context)
    return db
}