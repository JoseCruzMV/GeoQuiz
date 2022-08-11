package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { _: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { _: View ->
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener { _: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.previousButton.setOnClickListener { _: View ->
            if (currentIndex != 0) currentIndex--
            updateQuestion()
        }

        binding.questionTextView.setOnClickListener { _: View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        binding.questionTextView.setText(questionBank[currentIndex].textResId)
        enableButtons(state = true)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageId = if (userAnswer == correctAnswer) {
            R.string.correct_toast.also { score++ }
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
        enableButtons(state = false)
        if (currentIndex == questionBank.lastIndex) {
            printScore()
        }
    }

    private fun enableButtons(state: Boolean) {
        binding.apply {
            trueButton.isEnabled = state
            falseButton.isEnabled = state
        }
    }

    private fun printScore() {
        val maxScore = questionBank.size
        val finalScore = (score / maxScore.toFloat()) * 100
        Toast.makeText(this, "Your score is ${finalScore.toInt()}%!", Toast.LENGTH_SHORT).show()
        score = 0
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