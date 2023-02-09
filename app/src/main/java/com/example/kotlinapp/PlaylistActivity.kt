package com.example.kotlinapp


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlaylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

    }

    override fun onResume() {
        super.onResume()

        Player.handleComponents(activity = this,  usesCardLayout = true)

        val playlistRecycler : RecyclerView = findViewById(R.id.recyclerviewPlayList)
        val adapter = PlayListAdapter(playlists)

        playlistRecycler.adapter = adapter



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