package com.lemonlab.kchallengeapp.repository

import androidx.lifecycle.LiveData
import com.lemonlab.kchallengeapp.data.QuestionDao
import com.lemonlab.kchallengeapp.model.Question

class QuestionRepository(private val questionDao: QuestionDao) {

    val radAllData : LiveData<List<Question>> = questionDao.readAllData()

    suspend fun addQuestion(question : Question){
        questionDao.addQuestion(question)
    }

    suspend fun updateQuestion(question: Question){
        questionDao.updateQuestion(question)
    }
}