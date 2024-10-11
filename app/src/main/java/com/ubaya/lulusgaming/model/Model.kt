package com.ubaya.lulusgaming.model

import java.io.Serializable

data class Schedule(
    var venue:String?,
    var eventName:String?,
    var gameName:String?,
    var datetime:String?,
    var description:String?,
    var team:String?
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