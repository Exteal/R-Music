package com.example.kotlinapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlaylistActivity : AppCompatActivity() {
    private val player = Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

    }

    override fun onResume() {
        super.onResume()

        player.activity = this


        val playlistRecycler : RecyclerView = findViewById(R.id.recyclerviewPlayList)
        val adapter = PlayListAdapter(playlists)
        val playerView : CardView = findViewById(R.id.playerCard)

        val fab : FloatingActionButton = findViewById(R.id.playFAB)


        playlistRecycler.adapter = adapter


        fab.setImageDrawable(AppCompatResources.getDrawable(applicationContext, R.mipmap.play))
        fab.setOnClickListener {

            when(val clickedPos = player.storedMusicPos) {
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


        val nav : BottomNavigationView = findViewById(R.id.nav)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page1 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else -> {}
            }
            true
        }

    }
}