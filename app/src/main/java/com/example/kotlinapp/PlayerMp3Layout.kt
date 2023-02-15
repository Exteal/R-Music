package com.example.kotlinapp

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

 class PlayerMp3Layout(activity: AppCompatActivity) : PlayerLayout(activity) {
    override fun onStopLayout() {
        val card: MaterialCardView = activity.findViewById(R.id.playerCard)
        card.visibility = View.INVISIBLE
    }

    override fun onPlayLayout() {
        val music = Player.playlist[Player.storedMusicPos]
        val description : TextView = activity.findViewById(R.id.playerDescription)
        val playerCard : CardView = activity.findViewById(R.id.playerCard)
        val btn : MaterialButton = activity.findViewById(R.id.pause)

        btn.icon = ContextCompat.getDrawable(activity, R.mipmap.pause)
        description.text = music.name
        playerCard.visibility = View.VISIBLE
    }



     override fun fromPauseLayout() {
         val btn : MaterialButton = activity.findViewById(R.id.pause)
         btn.icon = ContextCompat.getDrawable(activity, R.mipmap.pause)
     }

     override fun toPauseLayout() {
         val btn : MaterialButton = activity.findViewById(R.id.pause)
         btn.icon = ContextCompat.getDrawable(activity, R.mipmap.baseline_play_arrow_black_48)
     }
 }

