package com.example.simple1.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.simple1.R
import com.example.simple1.databinding.ActivityGameBinding
import com.example.simple1.framework.Interactors
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    lateinit var viewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGameBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_game)
        binding.lifecycleOwner = this


        var player1Name = intent.extras?.getString("player1")
        var player2Name = intent.extras?.getString("player2")

        //initialize viewModel
        viewModel = ViewModelProviders.of(this,
            GameViewModelFactory(Interactors))
            .get(GameViewModel::class.java)
        viewModel.startPlaying(player1Name!!,player2Name!!)
        binding.viewModel = viewModel

        btnGo.setOnClickListener {
            viewModel.submitResult(etWord.text)
        }
        viewModel.winner.observe(this, Observer {
            intent = Intent(this, EndGameActivity::class.java)
            intent.putExtra("winner" , it.name)
            intent.putExtra("score" , it.score)
            finish()
            startActivity(intent)

        })
    }

    override fun onBackPressed() {

    }
}
