package com.ubaya.lulusgaming.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.lulusgaming.util.DB_NAME

@Database(entities = arrayOf(Account::class), version = 1)
abstract class AccountDatabase:RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object{
        @Volatile private var instance: AccountDatabase ?= null
        private val LOCK = Any()

        //Create Database
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, AccountDatabase::class.java,
            DB_NAME)
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