package com.example.catchtheobjectgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catchtheobjectgame.data.ScoreDatabase
import com.example.catchtheobjectgame.data.ScoreEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Наприклад, змінна для збереження рахунку
    private var finalScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Тут підключається activity_main.xml, у якому <DrawView />
        setContentView(R.layout.activity_main)

        // Тут можна додати логіку, якщо треба
    }

    // Викличеться при Game Over
    fun gameOver(score: Int) {
        finalScore = score
        saveScoreToDb(finalScore)
        finish() // або перехід кудись ще
    }

    private fun saveScoreToDb(score: Int) {
        val entity = ScoreEntity(
            score = score,
            dateTime = System.currentTimeMillis()
        )
        CoroutineScope(Dispatchers.IO).launch {
            ScoreDatabase.getDatabase(this@MainActivity)
                .scoreDao()
                .insertScore(entity)
        }
    }
}
