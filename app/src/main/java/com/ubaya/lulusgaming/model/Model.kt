package com.ubaya.lulusgaming.model

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
    var photoUrl:String?
)