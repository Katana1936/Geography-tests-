package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
private const val KEY_IS_CHEATER = "is_cheater"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private var answerIsTrue = false
    private var isCheater = false
    private lateinit var showAnswerButton: Button
    private lateinit var backButton: ImageButton

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        if (savedInstanceState != null) {
            isCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER, false)
        }

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        backButton = findViewById(R.id.backButton)

        showAnswerButton.setOnClickListener {
            val answerText = if (answerIsTrue) R.string.true_button else R.string.false_button
            answerTextView.setText(answerText)
            isCheater = true
            setAnswerShownResult(true)
        }

        backButton.setOnClickListener {
            onBackPressed()
        }

        updateQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_CHEATER, isCheater)
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        answerTextView.setText(questionTextResId)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
