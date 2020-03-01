package com.example.core.domain

enum class PlayerState{
    PLAYING,
    WAITING_OTHER_TO_PLAY,
}
data class Player(var id:Int, var name:String
                  ,var state: PlayerState, var score:Int , var lastWord:String)