package com.hacktj.studystudio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.database.*


class Calendar : Fragment() {



    private lateinit var calendarView: CalendarView
    private lateinit var editText: EditText
    private lateinit var stringDateSelected: String
    private lateinit var databaseReference: DatabaseReference
    private lateinit var addEventButton: Button

    private fun calendarClicked() {
        databaseReference.child(stringDateSelected)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        editText.setText(snapshot.value.toString())
                    } else {
                        editText.setText("Name")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarView = view.findViewById(R.id.calendarView)
        editText = view.findViewById(R.id.editText)
        addEventButton = view.findViewById(R.id.button5)

        calendarView.setOnDateChangeListener { calendarView, i, i1, i2 ->
            stringDateSelected = i.toString() + i1.toString() + i2.toString()
            calendarClicked()
        }
        addEventButton.setOnClickListener {
            buttonSaveEvent(it)
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")
        return view
    }

    private fun buttonSaveEvent(view: View) {
        databaseReference.child(stringDateSelected).setValue(editText.text.toString())
    }



}