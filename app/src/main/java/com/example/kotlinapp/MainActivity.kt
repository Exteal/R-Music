package com.example.kotlinapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val recycler : RecyclerView = findViewById(R.id.recyclerview)
        val musicAdapter = MusicAdapter(musics)
        recycler.adapter = musicAdapter

        val musicSeparator = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(recycler.context, R.drawable.divider)
        drawable?.let {
            musicSeparator.setDrawable(it)
            recycler.addItemDecoration(musicSeparator)
        }


        val playerView : CardView = findViewById(R.id.playerCard)
        val player = Player(this)

        player.handleComponents()

        val fab : FloatingActionButton = findViewById(R.id.playFAB)
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

        val nav : BottomNavigationView = findViewById(R.id.nav)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page1 -> false
                R.id.page2 -> {
                    val intent = Intent(this, PlaylistActivity::class.java)
                    startActivity(intent)

                    true
                }
                R.id.page3 -> false
                else -> false

            }
        }
    }

}