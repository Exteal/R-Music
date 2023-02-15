package com.example.kotlinapp

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class PlayerGrooveLayout(activity: AppCompatActivity) : PlayerLayout(activity) {

    override fun onStopLayout() {

    }

    override fun onPlayLayout() {
        val btn : MaterialButton = activity.findViewById(R.id.pause)
        btn.icon = ContextCompat.getDrawable(activity, R.mipmap.baseline_pause_circle_outline_white_48)

        val music = Player.playlist[Player.storedMusicPos]
        val description : TextView = activity.findViewById(R.id.playerDescription)
        description.text = "${music.artist} - ${music.name}"
    }

    override fun fromPauseLayout() {
        val btn : MaterialButton = activity.findViewById(R.id.pause)
        btn.icon = ContextCompat.getDrawable(activity, R.mipmap.baseline_pause_circle_outline_white_48)

    }

    override fun toPauseLayout() {
        val btn : MaterialButton = activity.findViewById(R.id.pause)
        btn.icon = ContextCompat.getDrawable(activity, R.mipmap.baseline_play_circle_outline_white_48)
    }
}