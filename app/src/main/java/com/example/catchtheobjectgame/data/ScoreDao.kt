package com.example.catchtheobjectgame.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScore(score: ScoreEntity)

    @Query("SELECT * FROM scores ORDER BY id DESC")
    fun getAllScores(): List<ScoreEntity>

    @Query("SELECT * FROM scores WHERE id = :scoreId LIMIT 1")
    fun getScoreById(scoreId: Int): ScoreEntity?
}
