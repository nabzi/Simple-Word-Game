package com.example.simple1.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simple1.R
import kotlinx.android.synthetic.main.activity_enter.*

class EnterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        btnNext.setOnClickListener {
            var intent = Intent(this, GameActivity::class.java)
            intent.putExtra("player1" , etPlayer1.text.toString())
            intent.putExtra("player2" , etPlayer2.text.toString())
            startActivity(intent)
        }
    }
}
