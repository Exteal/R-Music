package com.example.kotlinapp

import android.content.Context
import android.media.MediaPlayer

import androidx.cardview.widget.CardView

class Player(context: Context) : CardView(context) {

    companion object {
        var player = MediaPlayer()
        lateinit var music : Music
    }

    fun playMusic() {

        player.release()  //TODO -> player.reset()
        player = MediaPlayer.create(context, music.getSound())
        player.setOnPreparedListener { player.start() }

        /*
        player.setAudioAttributes(AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setLegacyStreamType(AudioManager.STREAM_MUSIC)
            .build())

        val res = resources.openRawResourceFd(music.getSound())
        if (res == null) {
          Log.d("TAG", "FD NULL")
        }
        else {
            player.setDataSource(res.fileDescriptor)
            player.prepare()
            player.start()
            //
        }*/
    }
}