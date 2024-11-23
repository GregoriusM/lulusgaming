package com.ubaya.lulusgaming.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class Schedule(
    var date:String?,
    var month:String?,
    var eventName:String?,
    var gameName:String?,
    var team:String?,
    var location:String?,
    var time:String?,
    var eventDesc:String?,
    var urlEvent:String?
)

data class Game(
    var name:String?,
    var gameDesc:String?,
    var photoUrl:String?,
    var achievements:ArrayList<Achievement>
) : Serializable

data class Achievement(
    var tournament:String?,
    var year:String?,
    var team: String?
) : Serializable

@Entity
data class Account(
    @ColumnInfo(name = "firstName")
    var firstName:String,
    @ColumnInfo(name = "lastName")
    var lastName:String,

    @PrimaryKey
    @ColumnInfo(name = "username")
    var username:String,

    @ColumnInfo(name = "password")
    var password:String,
)