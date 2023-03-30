package com.example.kotlinapp

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class RadioFragment : Fragment(R.layout.layout_radio_fragment) {

    private fun startStream(player : MediaPlayer) {
        player.reset()
        player.setAudioAttributes(AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA).build())

        val url = "https://listen.moe/fallback"
        player.setDataSource(url)

        player.setOnPreparedListener {
            Log.d("ERRMED","tttttt")
            player.start()
        }
        player.setOnErrorListener { mediaPlayer, _, _ ->
            Log.d("ERRMED","yayayayayayayay")
            startStream(mediaPlayer)
            true
        }
        player.prepareAsync()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn : MaterialButton = view.findViewById(R.id.playRadio)
        btn.setOnClickListener {
            val player = Player.getPlayer()
            startStream(player)
        }
    }

}