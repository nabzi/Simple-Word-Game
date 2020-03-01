package com.example.core.interactors

import com.example.core.data.GameRepository
import com.example.core.domain.Player

class GetGameWinner(private val gameRepository: GameRepository){
        suspend fun execute( ) : Player?{
            return gameRepository.getWinner()
        }
}