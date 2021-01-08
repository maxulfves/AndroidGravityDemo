package com.example.gravitydemo

import android.util.Log

class Wall {

    var standing = true

    fun giveImpulse(eK: Float) {
        Log.d("TAG", "HP: " + health.toString())
        health -= eK/100
    }

    var health = 100f;

    val x = 5.5f
    val y = 0.5f
    var h:Float = 2.25f
    val w:Float = 0.25f;

}