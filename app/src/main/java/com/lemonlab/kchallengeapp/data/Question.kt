package com.lemonlab.kchallengeapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(

    @PrimaryKey(autoGenerate = true)
    val id : Int ,
    val category : String ,
    val questionText : String,
    val answerOne : String,
    val answerTwo : String,
    val answerThree : String,
    val answerFour : String,
    val answerIndex: Int
)