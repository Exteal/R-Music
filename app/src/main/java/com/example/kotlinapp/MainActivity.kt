package com.example.kotlinapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val player = Player(applicationContext)

        val fab : FloatingActionButton = findViewById(R.id.playFAB)
        fab.setImageDrawable(AppCompatResources.getDrawable(applicationContext, R.drawable.play))
        fab.setOnClickListener { if(selectedMusicPos != null) player.playMusic() }
    }

}