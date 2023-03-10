package com.hacktj.studystudio

import android.app.NotificationManager
import android.content.Context
import android.media.AudioManager
import android.media.MediaRecorder.AudioSource
import android.os.Bundle
import android.provider.MediaStore.Audio
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment


class Settings : Fragment() {
    private lateinit var dateFormatSwitch: SwitchCompat
    private lateinit var alarmSoundSwitch: SwitchCompat
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val audioManager = requireContext().getSystemService(AudioManager::class.java)
        dateFormatSwitch = view.findViewById(R.id.switch1)
        val temp = activity?.getPreferences(Context.MODE_PRIVATE)
        dateFormatSwitch.isChecked = temp!!.getBoolean("international_format", false)
        dateFormatSwitch.setOnClickListener {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPref!!.edit()
            if (dateFormatSwitch.isChecked) {
                editor.putBoolean("international_format", true)
            } else {
                editor.putBoolean("international_format", false)
            }
            editor.apply()
        }
        alarmSoundSwitch = view.findViewById(R.id.switch2)
        alarmSoundSwitch.isChecked = temp.getBoolean("alarm_sound", false)
        alarmSoundSwitch.setOnClickListener {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val editor = sharedPref!!.edit()
            if (alarmSoundSwitch.isChecked) {
                audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
                editor.putBoolean("alarm_sound", true)
            } else {
                audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
                editor.putBoolean("alarm_sound", false)
            }
            editor.apply()
        }
        return view
    }


}