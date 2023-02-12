package com.example.kotlinapp


import androidx.appcompat.app.AppCompatActivity


abstract class PlayerLayout(val activity: AppCompatActivity) {
    abstract  fun onStopLayout()
    abstract fun onPlayLayout()
    abstract fun updateFabLayout()
}