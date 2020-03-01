package com.example.core.domain

data class WordGame (var players: ArrayList<Player>,
                     var lastWord:String = "",
                     val QUESTION_LENGTH:Int = 1,
                     val WIN_SCORE:Int = 10){
    var lastId = 0

    fun getQuestion():String{
        var s = lastWord
        if (s.length> 1) {
            return s.substring(s.length - QUESTION_LENGTH , s.length)
        }
        return "a"
    }
    fun checkResultAndSetScore(playerId:Int , newWord:String) : Boolean{

        var result = false

        players.iterator().forEach {
            if (it.id == playerId) {
                it.state = PlayerState.WAITING_OTHER_TO_PLAY
                if (newWord.equals("")) {
                    it.score--
                    result = false
                } else if (newWord.startsWith(getQuestion())) {
                    //else if (newWord.length ==3){
                    it.score++
                    result = true
                } else {
                    it.score--
                    result = false
                }
            }
        }

        getOtherPlayer(playerId)?.apply{
            state = PlayerState.PLAYING
        }
        lastWord = newWord
        return result
    }

    fun getWinner(): Player? {
        var loser: Player
        players.iterator().forEach {
            if (it.score > WIN_SCORE) {
                return it
            }
            if (it.score < 0) {
                loser = it
                return getOtherPlayer(it.id)
            }
        }
        return null
    }
    fun getOtherPlayer(thisPlayerId : Int):Player?{
        players.iterator().forEach {
            if (it.id != thisPlayerId) {
                return it
            }
        }
        return null
    }

    fun addPlayer(playerName:String): Player {
        return Player(++lastId,
            playerName,
            PlayerState.PLAYING,
            0,
            "")
            .also {
                players.add(it)
            }
    }
}