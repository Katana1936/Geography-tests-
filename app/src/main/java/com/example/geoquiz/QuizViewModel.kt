package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = if (currentIndex - 1 < 0) {
            questionBank.size - 1
        } else {
            (currentIndex - 1) % questionBank.size
        }
    }
    var currentIndex = 0

    private val questiunBan = listof(
        Questoin(R.string.question_australia,true),
        Questoin(R.string.question_oceans,true),
        Questoin(R.string.question_mideast,true),
        Questoin(R.string.question_africa,true),
        Questoin(R.string.question_americas,true),
        Questoin(R.string.question_asia,true)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size

}
