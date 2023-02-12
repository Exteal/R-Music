package com.example.kotlinapp

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

class MusicActivity : AppCompatActivity() {
    private var num by Delegates.notNull<Int>()
    private lateinit var music: Music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.activityMusicPlayerFragment, PlayerGrooveFragment())
                .commit()

        }
    }

    override fun onResume() {
        super.onResume()

        GroovePlayer.handleComponents(activity = this, layoutType = PlayerGrooveLayout::class.java)

        music  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("music", Music::class.java) as Music
        else intent.getSerializableExtra("music") as Music

        val playlist = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("playlist", PlayList::class.java) as PlayList
        else intent.getSerializableExtra("playlist") as PlayList



        val musicList : ArrayList<Music> = playlist.musicList
        num = musicList.indexOf(music)

        val playingSong : TextView = findViewById(R.id.playerDescription)
        playingSong.text = "${music.artist} - ${music.name}"

        val nextButton : Button = findViewById(R.id.activityMusicNextSong)
        val previousButton : Button = findViewById(R.id.activityMusicPreviousSong)
        val returnButton : Button = findViewById(R.id.musicActivityBack)


        previousButton.setOnClickListener {
            clickPreviousListener(musicList)
        }

        nextButton.setOnClickListener {
            clickNextListener(musicList)
        }


        returnButton.setOnClickListener {
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
