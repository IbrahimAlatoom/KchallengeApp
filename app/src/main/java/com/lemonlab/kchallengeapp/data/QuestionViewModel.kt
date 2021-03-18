package com.lemonlab.kchallengeapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData : LiveData<List<Question>>
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