package com.ubaya.lulusgaming.util

import android.content.Context
import com.ubaya.lulusgaming.model.AccountDatabase

val DB_NAME = "lulusgamingdb"

fun buildDb(context: Context):AccountDatabase{
    val db = AccountDatabase.buildDatabase(context)
    return db
}