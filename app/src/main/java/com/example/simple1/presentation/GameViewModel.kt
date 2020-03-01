package com.example.simple1.presentation

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.domain.Player
import com.example.core.domain.PlayerState
import com.example.simple1.framework.Interactors
import kotlinx.coroutines.*


class GameViewModel(protected val interactors: Interactors)  : ViewModel()
 , CoroutineScope by MainScope(){
    val TIME_FOR_ANSWER_SEC  = 10
    var time = TIME_FOR_ANSWER_SEC
    var answerTime: MutableLiveData<Int> = MutableLiveData()
    var questionCharacters: MutableLiveData<String> = MutableLiveData()
    var canWriteResponse: MutableLiveData<Boolean> = MutableLiveData()
    var winner: MutableLiveData<Player> = MutableLiveData()
    var player = MutableLiveData<Player>()
    var otherPlayer = MutableLiveData<Player>()
    var countDownJob : Job?  = null
    fun startPlaying(player1Name:String, player2Name: String){
        runBlocking {
             val player1 = interactors.addPlayer.execute(player1Name)
             val player2 = interactors.addPlayer.execute(player2Name)
             player.postValue(player1)
             otherPlayer.postValue(player2)
        }

        startNewTurn()
    }
    private suspend fun countDown() {
        time--
        if(time > -1) {
            answerTime.postValue(time)
            delay(1000)
            countDown()
        }else
        {
            countDownJob = null
            //disable word input
            canWriteResponse.postValue(false)
        }
    }

    private fun startNewTurn() {
        canWriteResponse.postValue(true)
        otherPlayer.postValue(player.value)
        answerTime.postValue(TIME_FOR_ANSWER_SEC)
        GlobalScope.launch {
            player.postValue(interactors.getNextPlayer.execute()?.apply
            { lastWord = "" })
            questionCharacters.postValue(interactors.getQuestion.execute())
        }
        time = TIME_FOR_ANSWER_SEC
        countDownJob = launch {
            countDown()
        }
    }


    fun submitResult(word: Editable?) {
        player.postValue(player.value?.apply { lastWord = word.toString() } )
        countDownJob?.cancel()
        GlobalScope.launch {
            interactors.setPlayerNewResult
                .execute(player.value!!.id,player.value!!.lastWord)

            //check if there anyone has lost the game
            interactors.getWinner.execute()?.let{
                winner.postValue(it)
            }
            startNewTurn()
        }
    }

}
class GameViewModelFactory(protected val interactors: Interactors)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(interactors) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}