package com.example.core.interactors

import com.example.core.data.GameRepository
import com.example.core.domain.Player

class AddPlayer (private val gameRepository: GameRepository){
    suspend fun execute(playerName: String):Player
    {
        return gameRepository.addPlayer(playerName)
    }
}