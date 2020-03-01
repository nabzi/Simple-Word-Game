package com.example.core.interactors

import com.example.core.data.GameRepository

class SetPlayerNewResult (private val gameRepository: GameRepository){
    suspend fun execute(id:Int,lastWord:String) {
        gameRepository.setPlayerNewResult(id, lastWord)
    }
}