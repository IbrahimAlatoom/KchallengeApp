package com.lemonlab.kchallengeapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lemonlab.kchallengeapp.model.Question

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)


    @Update
    suspend fun updateQuestion(question: Question)


    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun readAllData():LiveData<List<Question>>
}