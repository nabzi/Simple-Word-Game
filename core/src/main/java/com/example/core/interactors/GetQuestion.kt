package com.example.core.interactors

import com.example.core.data.GameRepository

class GetQuestion (private val gameRepository: GameRepository){
    suspend fun execute( charNumber : Int = 1) : String{
        return gameRepository.getQuestion()
    }
}