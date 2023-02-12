package com.example.kotlinapp

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlayerGrooveLayout(activity: AppCompatActivity) : PlayerLayout(activity) {

    override fun onStopLayout() {

    }

    override fun onPlayLayout() {
        val music = Player.playlist[Player.storedMusicPos]
        val description : TextView = activity.findViewById(R.id.playerDescription)
        description.text = "${music.artist} - ${music.name}"
    }
    override fun updateFabLayout() {
        val play : FloatingActionButton = activity.findViewById(R.id.play)
        play.setImageDrawable(AppCompatResources.getDrawable(activity.applicationContext, R.mipmap.play))
    }

    fun loopLayout() {

    }

}