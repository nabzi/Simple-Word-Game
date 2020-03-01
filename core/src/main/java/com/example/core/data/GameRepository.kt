package com.example.core.data

import com.example.core.domain.Player

class GameRepository(private val dataSource: GameDataSource) {
    suspend fun addPlayer (playerName: String) =
        dataSource.addPlayer(playerName)

    suspend fun getNextPlayer():Player? = dataSource.getNextPlayer()

    suspend fun setPlayerNewResult(id:Int , lastWord:String)
            = dataSource.setNewResult(id,lastWord)

    suspend fun getQuestion()
            = dataSource.getQUestion()

    suspend fun getWinner(): Player?
            = dataSource.getWinner()
}