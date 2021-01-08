package com.example.gravitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val th = SimulationThread();
        th.start();

        val myView = SimulationView(this, th);





        setContentView(myView)
    }


}