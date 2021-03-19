package com.lemonlab.kchallengeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lemonlab.kchallengeapp.data.QuestionDatabase
import com.lemonlab.kchallengeapp.repository.QuestionRepository
import com.lemonlab.kchallengeapp.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Question>>
    private val repository : QuestionRepository

    init {
        val questionDao = QuestionDatabase.getDatabase(application).questionDao()
        repository = QuestionRepository(questionDao)
        readAllData = repository.radAllData
    }
    fun addQuestion(question: Question){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addQuestion(question)
        }
    }
}