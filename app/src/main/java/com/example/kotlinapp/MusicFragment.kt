package com.example.kotlinapp

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MusicFragment : Fragment(R.layout.layout_music_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler : RecyclerView = view.findViewById(R.id.recyclerview)
        val musicAdapter = MusicAdapter(musics)
        recycler.adapter = musicAdapter

        val musicSeparator = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(recycler.context, R.drawable.divider)
        drawable?.let {
            musicSeparator.setDrawable(it)
            recycler.addItemDecoration(musicSeparator)
        }


        super.onViewCreated(view, savedInstanceState)
    }

}
