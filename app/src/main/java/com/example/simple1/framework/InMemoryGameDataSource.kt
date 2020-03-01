package com.example.simple1.framework

import com.example.core.data.GameDataSource
import com.example.core.domain.Player
import com.example.core.domain.PlayerState
import com.example.core.domain.WordGame

class InMemoryGameDataSource : GameDataSource {

    var game=WordGame(arrayListOf<Player>())

    override suspend fun addPlayer(playerName: String) : Player{
        return game.addPlayer(playerName)
    }

    override suspend fun getNextPlayer(): Player? {
        game.players.iterator().forEach {
            if(it.state.equals(PlayerState.PLAYING))
                return it
        }
        return null
    }

    override suspend fun setNewResult(id: Int, lastWord: String) {
        game.players.iterator().forEach {
            if(it.id == id)
            {
                it.lastWord = lastWord
            }
        }
        game.checkResultAndSetScore(id, lastWord)
    }

    override suspend fun getQUestion(): String {
       return game.getQuestion()
    }

    override suspend fun getWinner(): Player? {
        game.getWinner()?.let{
            return it.also {
                game = WordGame(arrayListOf<Player>())
            }
        }
        return null
    }
}