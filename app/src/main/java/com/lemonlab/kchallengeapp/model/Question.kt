package com.lemonlab.kchallengeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
):Parcelable