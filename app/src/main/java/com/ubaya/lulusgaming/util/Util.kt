package com.ubaya.lulusgaming.util

import android.content.Context
import com.ubaya.lulusgaming.model.AccountDatabase
import com.ubaya.lulusgaming.model.ApplyTeamDatabase

val DB_NAME = "lulusgamingdb"
val APPLY_TEAM_DB_NAME = "apply_team_db"

fun buildDb(context: Context):AccountDatabase{
    val db = AccountDatabase.buildDatabase(context)
    return db
}

fun buildApplyDb(context: Context):ApplyTeamDatabase{
    val db = ApplyTeamDatabase.buildDatabase(context)
    return db
}