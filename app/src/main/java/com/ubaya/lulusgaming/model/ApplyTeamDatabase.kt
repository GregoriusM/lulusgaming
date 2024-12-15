package com.ubaya.lulusgaming.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.lulusgaming.util.APPLY_TEAM_DB_NAME
import com.ubaya.lulusgaming.util.DB_NAME

@Database(entities = arrayOf(ApplyTeam::class), version = 1)
abstract class ApplyTeamDatabase:RoomDatabase() {
    abstract fun applyTeamDao(): ApplyTeamDao

    companion object{
        @Volatile private var instance: ApplyTeamDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ApplyTeamDatabase::class.java,
            APPLY_TEAM_DB_NAME)
            .build()

        operator fun invoke(context: Context){
            if(instance==null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}