package com.example.kotlinapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        Player.activity = this
        Player.handleComponents()

        val recycler : RecyclerView = findViewById(R.id.recyclerview)
        val musicAdapter = MusicAdapter(musics)
        recycler.adapter = musicAdapter

        val musicSeparator = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(recycler.context, R.drawable.divider)
        drawable?.let {
            musicSeparator.setDrawable(it)
            recycler.addItemDecoration(musicSeparator)
        }






        /*val fab : FloatingActionButton = findViewById(R.id.playFAB)
        fab.setImageDrawable(AppCompatResources.getDrawable(applicationContext, R.mipmap.play))

        fab.setOnClickListener {
            when(Player.storedMusicPos) {
                -1 -> {
                    Player.stop()
                    playerView.visibility = View.INVISIBLE
                }
                else -> {
                    Player.playMusic()
                    playerView.visibility = View.VISIBLE
                }
            }
        }
        */

        val search : SearchView = findViewById(R.id.Search)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")

            }
        })

        val nav : BottomNavigationView = findViewById(R.id.nav)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page1 -> true
                R.id.page2 -> {
                    val intent = Intent(this, PlaylistActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.page3 -> true
                else -> true
            }
        }
    }

}