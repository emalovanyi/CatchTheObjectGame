package com.example.catchtheobjectgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catchtheobjectgame.data.ScoreDatabase
import com.example.catchtheobjectgame.data.ScoreEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Приклад змінної для збереження фінального рахунку
    private var finalScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // activity_main містить ваш <com.example.catchtheobjectgame.DrawView ... />

        // Якщо ваша логіка рахунку зберігається у DrawView, можна через колбек отримувати score
        // або зберігати рахунок всередині MainActivity. Припустимо, що коли гра закінчується,
        // викликається метод gameOver(finalScore).
    }

    fun gameOver(score: Int) {
        finalScore = score
        // Зберегти score у Room:
        saveScoreToDb(finalScore)
        // Закрити активність або перейти на інший екран
        finish()
    }

    private fun saveScoreToDb(score: Int) {
        val entity = ScoreEntity(
            score = score,
            dateTime = System.currentTimeMillis()
        )
        CoroutineScope(Dispatchers.IO).launch {
            ScoreDatabase.getDatabase(this@MainActivity).scoreDao().insertScore(entity)
        }
    }
}
