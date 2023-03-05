package com.hacktj.studystudio

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hacktj.studystudio.databinding.ActivityMainBinding


class Home : Fragment() {
    private lateinit var calendar: Calendar
    private lateinit var streakText: TextView
    private lateinit var dateText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        calendar = Calendar.getInstance()
        dateText = view.findViewById(R.id.dateText)
        dateText.text = getDate()
        streakText = view.findViewById(R.id.streakText)
        streakText.text = getString(R.string.streak, 0)
        dateText.text = getDate()
        return view
    }

    private fun getDate(): String {
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        return "$month/$day/$year"
    }

    private fun getQuote() {
        // Find api and grab quote
    }


}