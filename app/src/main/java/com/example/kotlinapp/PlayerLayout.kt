package com.example.kotlinapp


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.floatingactionbutton.FloatingActionButton


abstract class PlayerLayout(val activity: AppCompatActivity) {
    abstract  fun onStopLayout()
    abstract fun onPlayLayout()
    fun setFabIcon() {
        val play : FloatingActionButton = activity.findViewById(R.id.play)
        play.setImageDrawable(AppCompatResources.getDrawable(activity.applicationContext, R.mipmap.play))
    }

    abstract fun fromPauseLayout()
    abstract fun toPauseLayout()
}