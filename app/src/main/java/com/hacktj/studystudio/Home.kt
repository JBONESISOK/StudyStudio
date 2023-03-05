package com.hacktj.studystudio

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import kotlinx.coroutines.MainScope
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class Home : Fragment() {
    private lateinit var calendar: Calendar
    private lateinit var streakText: TextView
    private lateinit var dateText: TextView
    private var internationalFormat : Boolean = false
    private val dateFormat: DateFormat = SimpleDateFormat("MM-dd-yy", Locale.US)
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        calendar = Calendar.getInstance()
        dateText = view.findViewById(R.id.dateText)
        dateText.text = getDate()
        streakText = view.findViewById(R.id.streakText)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        internationalFormat = sharedPref!!.getBoolean("international_format", false)
        val date = Date()
        val today = dateFormat.format(date)
        val lastLogin = getLastLoginDate()
        val yesterday = getYesterdayDate(dateFormat, date)
        if (lastLogin == null) {
            updateLastLoginDate(today)
            incrementDays()
        } else {
            if (lastLogin == yesterday) {
                updateLastLoginDate(today)
                incrementDays()
            } else if (lastLogin != today) {
                updateLastLoginDate(today)
                resetDays()
            }
        }
        streakText.text = getString(R.string.streak, getConsecutiveDays())
        dateText.text = getDate()
        return view
    }

    private fun getDate(): String {
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        return if (internationalFormat) {
            "$day/$month/$year"
        } else {
            "$month/$day/$year"
        }
    }

    private fun updateLastLoginDate(date: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref!!.edit()
        editor.putString("last_login_day", date)
        editor.apply()
    }

    private fun getConsecutiveDays(): Int {
        var days = 0
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        days = sharedPref!!.getInt("num_consecutive_days", 0)
        return days
    }

    private fun getLastLoginDate(): String? {
        var lastLogin: String? = null
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        lastLogin = sharedPref?.getString("last_login_day", null)
        return lastLogin
    }

    private fun incrementDays() {
        val streak = getConsecutiveDays() + 1
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val editor: Editor = sharedPref!!.edit()
        editor.putInt("num_consecutive_days", streak)
        editor.apply()

    }

    private fun resetDays() {
        val days = 1
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref!!.edit()
        editor.putInt("num_consecutive_days", days)
        editor.apply()
    }

    private fun getYesterdayDate(simpleDateFormat: DateFormat, date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, -1)
        return simpleDateFormat.format(calendar.time)
    }


}