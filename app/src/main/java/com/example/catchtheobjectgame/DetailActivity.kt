package com.example.catchtheobjectgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.catchtheobjectgame.data.ScoreDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var textScoreDetail: TextView
    private lateinit var textDateDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textScoreDetail = findViewById(R.id.textScoreDetail)
        textDateDetail = findViewById(R.id.textDateDetail)

        val scoreId = intent.getIntExtra(EXTRA_SCORE_ID, -1)
        if (scoreId != -1) {
            loadScore(scoreId)
        }
    }

    private fun loadScore(scoreId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = ScoreDatabase.getDatabase(this@DetailActivity).scoreDao()
            val scoreEntity = dao.getScoreById(scoreId)
            scoreEntity?.let {
                withContext(Dispatchers.Main) {
                    textScoreDetail.text = "Score: ${it.score}"
                    val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                    textDateDetail.text = "Date: ${sdf.format(Date(it.dateTime))}"
                }
            }
        }
    }

    companion object {
        private const val EXTRA_SCORE_ID = "extra_score_id"

        fun newIntent(context: Context, scoreId: Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SCORE_ID, scoreId)
            return intent
        }
    }
}
