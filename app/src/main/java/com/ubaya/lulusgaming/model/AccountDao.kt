package com.ubaya.lulusgaming.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAccount(vararg accounts: Account)

    @Query("SELECT * FROM account WHERE username = :username")
    fun selectAccount(username:String): Account
}