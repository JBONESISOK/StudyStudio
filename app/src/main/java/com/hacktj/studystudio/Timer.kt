package com.hacktj.studystudio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class Timer : Fragment() {
    private lateinit var quoteText: TextView
    private val scope = MainScope()
    private var minutes: Int = 0
    private var seconds: Int = 0
    private lateinit var addButton: Button
    private lateinit var decrementButton: Button
    private lateinit var startButton: Button
    private lateinit var minutesText: TextView
    private lateinit var secondsText: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)
        val colonText: TextView = view.findViewById(R.id.colonText)
        colonText.text = ":"
        minutesText = view.findViewById(R.id.minutesText)
        secondsText = view.findViewById(R.id.secondsText)
        minutesText.text = "00"
        secondsText.text = "00"
        quoteText = view.findViewById(R.id.quoteView)
        quoteText.text = motivationalQuotes.random()
        addButton = view.findViewById(R.id.incrementMinutes)
        addButton.setOnClickListener {
            minutes += 10
            minutesText.text = minutes.toString()
        }
        decrementButton = view.findViewById(R.id.decrementMinutes)
        decrementButton.setOnClickListener {
            if (minutes >= 10) {
                minutes -= 10
                minutesText.text = minutes.toString()
            }
        }
        startButton = view.findViewById(R.id.startButton)
        startButton.setOnClickListener {
            startTimer()
        }
        return view
    }

    private fun startTimer() {
        scope.launch {
            while (minutes != 0 || seconds != 0) {
                delay(1000)
                if (seconds == 0) {
                    seconds = 60
                    minutes--
                }
                println(seconds)
                seconds--
                secondsText.text = seconds.toString()
                minutesText.text = minutes.toString()
            }
        }
    }

}