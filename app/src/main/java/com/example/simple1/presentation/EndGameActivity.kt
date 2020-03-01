package com.example.simple1.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simple1.R
import kotlinx.android.synthetic.main.activity_end_game.*
import org.w3c.dom.EntityReference

class EndGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)
        var winnerName = intent.extras?.get("winner")
        var winnerScore = intent.extras?.get("score")
        tvResult.text = "$winnerName is the winner, with score $winnerScore"
        btnRestart.setOnClickListener {
            startActivity(Intent(this, EnterActivity::class.java))
        }
    }
}
