package com.example.kotlinapp

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class MusicActivity() : AppCompatActivity() {
    var num by Delegates.notNull<Int>()
    lateinit var music: Music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
    }

    override fun onResume() {
        super.onResume()


        music  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("music", Music::class.java) as Music
        else intent.getSerializableExtra("music") as Music

        val bundled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("playlist", ArrayList::class.java) as ArrayList<*>
        else intent.getSerializableExtra("playlist") as ArrayList<*>



        val playlist : ArrayList<Music> = bundled[0] as ArrayList<Music>
        num = playlist.indexOf(music)


        val nextButton : Button = findViewById(R.id.activityMusicNextSong)
        val previousButton : Button = findViewById(R.id.activityMusicPreviousSong)
        val backButton : Button = findViewById(R.id.musicActivityBack)

        previousButton.setOnClickListener {
            clickPreviousListener(playlist)
        }

        nextButton.setOnClickListener {
            clickNextListener(playlist)
        }


        backButton.setOnClickListener {
            finish()
        }

        layoutMusic(music)

    }

    private fun layoutMusic(music: Music) {

        val imageView : ImageView = findViewById(R.id.activityMusicImage)
        val artistText : TextView = findViewById(R.id.activityMusicArtistText)
        val songText : TextView = findViewById(R.id.activityMusicSongText)

        imageView.setImageResource(music.getImage())
        artistText.text = music.artist
        songText.text = music.name

    }

    private fun clickPreviousListener(playlist : ArrayList<Music>) {
        if ((num-1) in playlist.indices) {
            num--
            music = playlist[num]
            layoutMusic(music)
        }
    }

    private fun clickNextListener(playlist : ArrayList<Music>) {
        if ((num+1) in playlist.indices) {
            num++
            music = playlist[num]
            layoutMusic(music)
        }
    }
}
