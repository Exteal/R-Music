package com.example.kotlinapp


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

    }

    override fun onResume() {
        super.onResume()

        val playlistRecycler : RecyclerView = findViewById(R.id.recyclerviewPlayList)
        val adapter = PlayListAdapter(playlists)
        val playerView : CardView = findViewById(R.id.playerCard)
        val player = Player(this)
        val fab : FloatingActionButton = findViewById(R.id.playFAB)


        playlistRecycler.adapter = adapter

        fab.setImageDrawable(AppCompatResources.getDrawable(applicationContext, R.mipmap.play))
        fab.setOnClickListener {
            when(selectedMusicPos ) {
                -1 -> {
                    player.stop()
                    playerView.visibility = View.INVISIBLE
                }
                else -> {
                    player.playMusic()
                    playerView.visibility = View.VISIBLE

                }
            }
        }

    }
}