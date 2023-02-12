package com.example.kotlinapp

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

 class PlayerMp3Layout(activity: AppCompatActivity) : PlayerLayout(activity) {
    override fun onStopLayout() {
        val card: MaterialCardView = activity.findViewById(R.id.playerCard)
        card.visibility = View.INVISIBLE
    }

    override fun onPlayLayout() {
        val music = Player.playlist[Player.storedMusicPos]
        val description : TextView = activity.findViewById(R.id.playerDescription)
        val playerCard : CardView = activity.findViewById(R.id.playerCard)

        description.text = music.name
        playerCard.visibility = View.VISIBLE
    }
    override fun updateFabLayout() {
        val play : FloatingActionButton = activity.findViewById(R.id.play)
        play.setImageDrawable(AppCompatResources.getDrawable(activity.applicationContext, R.mipmap.play))
    }
}

