package com.ubaya.lulusgaming.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ApplyTeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg apply: ApplyTeam)

    @Query("SELECT * FROM applyteam")
    fun selectAllApplyTeam(): List<ApplyTeam>

    @Query("SELECT * FROM applyteam WHERE uuid = :id")
    fun selectApplyTeam(id:Int): ApplyTeam

    @Delete
    fun deleteApplyTeam(apply: ApplyTeam)
}