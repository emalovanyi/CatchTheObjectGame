package com.example.catchtheobjectgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catchtheobjectgame.data.ScoreDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreboardActivity : AppCompatActivity() {

    private lateinit var recyclerScores: RecyclerView
    private lateinit var adapter: ScoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        recyclerScores = findViewById(R.id.recyclerScores)
        recyclerScores.layoutManager = LinearLayoutManager(this)

        adapter = ScoreAdapter { scoreId ->
            openDetailActivity(scoreId)
        }
        recyclerScores.adapter = adapter

        loadScoresFromDb()
    }

    private fun loadScoresFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = ScoreDatabase.getDatabase(this@ScoreboardActivity).scoreDao()
            val scores = dao.getAllScores()
            withContext(Dispatchers.Main) {
                adapter.setScores(scores)
            }
        }
    }

    private fun openDetailActivity(scoreId: Int) {
        val intent = DetailActivity.newIntent(this, scoreId)
        startActivity(intent)
    }
}
