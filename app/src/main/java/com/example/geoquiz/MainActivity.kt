package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.geoquiz.databinding.ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }
}