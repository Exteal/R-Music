package com.example.kotlinapp

import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

object GroovePlayer {

    fun handleComponents(activity: MusicActivity, layoutType: Class<PlayerGrooveLayout>) {
        Player.handleComponents(activity, layoutType)

        val skipPrevious: MaterialButton = activity.findViewById(R.id.previous)
        val loop : MaterialButton = activity.findViewById(R.id.loop)
        val play : FloatingActionButton = activity.findViewById(R.id.play)

        skipPrevious.setOnClickListener {
            if(Player.isValidPosition(Player.playingMusicPos-1)) {
                Player.playingMusicPos--
                Player.storedMusicPos = Player.playingMusicPos
                play.performClick()
            }
            else {
                Player.playingMusicPos = Player.playlist.lastIndex
                Player.storedMusicPos = Player.playingMusicPos
                play.performClick()
            }
        }

        loop.setOnClickListener {
            Player.changeLoopState()
        }
    }
}