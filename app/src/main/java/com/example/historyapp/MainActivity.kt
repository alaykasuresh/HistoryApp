package com.example.historyapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    private val famousPeople = mapOf(
        "Pocahontas" to 22,
        "Diana, Princess of Wales" to 36,
        "Cleopatra" to 39,
        "Elvis Presley" to 42,
        "John F. Kennedy" to 46,
        "Grigori Rasputin" to 47,
        "Whitney Houston" to 48,
        "Michael Jackson" to 50,
        "William Shakespeare" to 52,
        "Julius Caesar" to 55,
        "Adolf Hitler" to 56,
        "Mahatma Gandhi" to 78,
        "Nikola Tesla" to 86,
        "Mother Teresa" to 87,
        "Queen Elizabeth" to 96
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val etAge = findViewById<EditText>(R.id.etAge)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val inputAge = etAge.text.toString().toIntOrNull()
            if (inputAge != null) {
                if (inputAge in 20..100) {
                    val closestMatch = findClosestAgeMatch(inputAge)
                    tvResult.text = "You share an age with $closestMatch, $closestMatch was a famous historical figure."
                } else {
                    showToast("This age is out of range")
                }
            } else {
                showToast("The age is an invalid format")
            }
        }

        btnClear.setOnClickListener {
            etAge.text.clear()
            tvResult.text = ""
        }
    }

    private fun findClosestAgeMatch(age: Int): String {
        var closestMatch = ""
        var closestDifference = Int.MAX_VALUE

        for ((person, personAge) in famousPeople) {
            val difference = (personAge - age).absoluteValue
            if (difference < closestDifference) {
                closestMatch = person
                closestDifference = difference
            }
        }

        return closestMatch
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }
}