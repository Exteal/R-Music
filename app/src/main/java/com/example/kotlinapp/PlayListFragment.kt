package com.example.kotlinapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class PlayListFragment : Fragment(R.layout.layout_playlist_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val playlistRecycler : RecyclerView = view.findViewById(R.id.recyclerview)
        val adapter = PlayListAdapter(playlists)

        playlistRecycler.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

}