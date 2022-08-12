package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QUizViewModel: $quizViewModel")

        binding.trueButton.setOnClickListener { _: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { _: View ->
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener { _: View ->
            quizViewModel.moveToNext()
            updateQuestion()
        }
        binding.questionTextView.setOnClickListener { _: View ->
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val currentQuestionText = quizViewModel.currentQuestionText
        binding.questionTextView.setText(currentQuestionText)
        enableButtons(state = true)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
        enableButtons(state = false)
    }

    private fun enableButtons(state: Boolean) {
        binding.apply {
            trueButton.isEnabled = state
            falseButton.isEnabled = state
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}