package com.example.catchtheobjectgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val startGameButton = findViewById<Button>(R.id.btnStartGame)
        val scoreboardButton = findViewById<Button>(R.id.btnScoreboard)

        startGameButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        scoreboardButton.setOnClickListener {
            val intent = Intent(this, ScoreboardActivity::class.java)
            startActivity(intent)
        }
    }
}
