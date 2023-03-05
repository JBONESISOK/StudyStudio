package com.hacktj.studystudio

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var editText: EditText
    private lateinit var stringDateSelected: String
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView = findViewById(R.id.calendarView)
        editText = findViewById(R.id.editText)


        calendarView.setOnDateChangeListener { calendarView, i, i1, i2 ->
            stringDateSelected = i.toString() + i1.toString() + i2.toString()
            calendarClicked()
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")
    }

    private fun calendarClicked() {
        databaseReference!!.child(stringDateSelected!!)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        editText!!.setText(snapshot.value.toString())
                    } else {
                        editText!!.setText("Name")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun buttonSaveEvent(view: View) {
        databaseReference.child(stringDateSelected).setValue(editText.text.toString())

    }

}