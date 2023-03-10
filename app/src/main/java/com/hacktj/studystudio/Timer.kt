package com.hacktj.studystudio

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
    private lateinit var stopButton: Button
    private var debounce: Boolean = false
    private var timerOn: Boolean = false
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
            if(!debounce) {
                startTimer()
            }
        }
        stopButton = view.findViewById(R.id.stopButton)
        stopButton.setOnClickListener {
            timerOn = false
            minutes = 0
            seconds = 0
            setTimerText()
        }
        return view
    }

    private fun startTimer() {
        timerOn = true
        debounce = true
        val previousTime = minutes
        scope.launch {
            while (timerOn && (minutes != 0)) {
                delay(1000)
                if (seconds <= 0) {
                    seconds = 59
                    minutes--
                } else {
                    seconds--
                }
                setTimerText()
                if(minutes <= previousTime - 30) {
                    println(minutes)
                    timerOn = false
                    Toast.makeText(context, "You may take a break now!", Toast.LENGTH_LONG).show()
                }
            }
            val alarmManager = requireActivity().getSystemService(AlarmManager::class.java)
            val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 0, alarmIntent)
            debounce = false
        }


    }

    private fun setTimerText() {
        if (seconds < 10) {
            secondsText.text = "0" + seconds
        } else {
            secondsText.text = seconds.toString()
        }
        if (minutes < 10) {
            minutesText.text = "0" + minutes
        } else {
            minutesText.text = minutes.toString()
        }
    }
}