package com.hacktj.studystudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var randomNum = (1 until 11).random()
        randomNum = randomNum.toInt()
        var randomText = ""
        if(randomNum == 1){
            randomText = "Good sleep is essential for learning."
        }
        else if(randomNum == 2) {
            randomText = "Mistakes are part of how you learn."
        }
        else if(randomNum == 3){
            randomText = "Listening to music can improve concentration and motivation to study!"
        }
        else if(randomNum == 4){
            randomText = "Remember to take frequent breaks."
        }
        else if(randomNum == 5){
            randomText = "Using colors are a great way to organize!"
        }
        else if(randomNum == 6){
            randomText = "Study smarter, not harder!"
        }
        else if(randomNum == 7){
            randomText = "Set study goals, they can "
        }
        else if(randomNum == 8){
            randomText = "Using colors are a great way to organize!"
        }
        else if(randomNum == 9){
            randomText = "Using colors are a great way to organize!"
        }
        else if(randomNum == 10){
            randomText = "Using colors are a great way to organize!"
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}