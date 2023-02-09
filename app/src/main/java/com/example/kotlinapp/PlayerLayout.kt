package com.example.kotlinapp

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

 object PlayerLayout {
    fun onStopLayout(activity: AppCompatActivity) {
        val card: MaterialCardView = activity.findViewById(R.id.playerCard)
        card.visibility = View.INVISIBLE
    }
    fun onPlayLayout(activity: AppCompatActivity) {
        val music = Player.playlist[Player.storedMusicPos]
        val description : TextView = activity.findViewById(R.id.playerDescription)
        val playerCard : CardView = activity.findViewById(R.id.playerCard)

        description.text = music.name
        playerCard.visibility = View.VISIBLE
    }
    fun updateFabLayout(activity: AppCompatActivity) {
        val fab : FloatingActionButton = activity.findViewById(R.id.playFAB)
        fab.setImageDrawable(AppCompatResources.getDrawable(activity.applicationContext, R.mipmap.play))
    }
}

