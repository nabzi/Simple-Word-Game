package com.example.core.data

import com.example.core.domain.Player

interface GameDataSource{
    suspend fun addPlayer(playerName:String):Player
    suspend fun getNextPlayer():Player?
    suspend fun setNewResult(playerId:Int, lastWord:String)
    suspend fun getQUestion(): String
    suspend fun getWinner(): Player?
}