package com.hacktj.studystudio

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*


class Home : Fragment() {
    private lateinit var calendar: Calendar
    private lateinit var streakText: TextView
    private lateinit var dateText: TextView
    var americanFormat = true

    private val scope = MainScope()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        return if(americanFormat) {
            "$month/$day/$year"
        } else {
            "$day/$month/$year"
        }
    }

}