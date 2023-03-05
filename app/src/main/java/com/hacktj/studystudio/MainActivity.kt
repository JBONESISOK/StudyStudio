package com.hacktj.studystudio

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var calendar: Calendar
    private lateinit var streakText: TextView
    private lateinit var dateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        setContentView(R.layout.survey)
        /*
        calendar = Calendar.getInstance()
        dateText = findViewById(R.id.dateView)
        streakText = findViewById(R.id.streakText)
        dateText.text = getDate()
        streakText.text = getString(R.string.streak, 0)
        */



    }
    private fun getDate(): String {
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        return "$month/$day/$year"
    }




}