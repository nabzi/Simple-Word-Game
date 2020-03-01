package com.example.simple1.framework

import com.example.core.data.GameRepository
import com.example.core.interactors.*

//singleton
object Interactors {
    var inMemoryGameDataSource : InMemoryGameDataSource = InMemoryGameDataSource()
    var repository = GameRepository(inMemoryGameDataSource)
    val addPlayer = AddPlayer(repository)
    val getNextPlayer= GetNextPlayer(repository)
    val setPlayerNewResult = SetPlayerNewResult(repository)
    val getQuestion = GetQuestion(repository)
    val getWinner = GetGameWinner(repository)
}