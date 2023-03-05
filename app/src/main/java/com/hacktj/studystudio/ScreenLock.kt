package com.hacktj.studystudio

import android.app.admin.DevicePolicyManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView


class ScreenLock : Fragment() {


    private lateinit var lockScreenView: ImageView
    private lateinit var unlockScreenView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen_lock, container, false)

        lockScreenView = view.findViewById(R.id.screenLock)
        lockScreenView.setOnClickListener {
            activity?.startLockTask()
        }
        unlockScreenView = view.findViewById(R.id.unlockScreen)
        unlockScreenView.setOnClickListener {
            activity?.stopLockTask()
        }
        return view
    }

}