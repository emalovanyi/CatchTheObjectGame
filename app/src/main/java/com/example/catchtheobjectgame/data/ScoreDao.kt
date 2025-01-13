package com.example.catchtheobjectgame.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {
    @Insert
    fun insertScore(score: ScoreEntity): Long

    @Query("SELECT * FROM scores")
    fun getAllScores(): List<ScoreEntity>

    @Query("SELECT * FROM scores WHERE id = :id LIMIT 1")
    fun getScoreById(id: Int): ScoreEntity?
}



