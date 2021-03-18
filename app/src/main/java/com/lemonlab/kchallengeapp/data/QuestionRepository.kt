package com.lemonlab.kchallengeapp.data

import androidx.lifecycle.LiveData

class QuestionRepository(private val questionDao: QuestionDao) {

    val radAllData : LiveData<List<Question>> = questionDao.readAllData()

    suspend fun addQuestion(question : Question){
        questionDao.addQuestion(question)
    }
}