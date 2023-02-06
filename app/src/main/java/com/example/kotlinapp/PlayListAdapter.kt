package com.example.kotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class PlayListAdapter(private val playlists: List<PlayList>) :
    RecyclerView.Adapter<PlayListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val playlistName : TextView
        private val musicsList : RecyclerView

        init {
            playlistName = view.findViewById(R.id.playlistName)
            musicsList = view.findViewById(R.id.playlistMusicsList)
        }

        fun bind(playlist : PlayList) {
            playlistName.text = playlist.name
            musicsList.adapter = MusicAdapter(playlist.musicList)

            val musicSeparator = DividerItemDecoration(musicsList.context, DividerItemDecoration.VERTICAL)
            val drawable = ContextCompat.getDrawable(musicsList.context, R.drawable.divider)
            drawable?.let {
                musicSeparator.setDrawable(it)
                musicsList.addItemDecoration(musicSeparator)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.playlist_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        val playlist = playlists[position]

        viewholder.bind(playlist)

        viewholder.itemView.setOnClickListener {
            val mu : RecyclerView = it.findViewById(R.id.playlistMusicsList)
            when(mu.visibility) {
                View.INVISIBLE -> {}
                View.VISIBLE -> {mu.visibility = View.GONE}
                View.GONE ->  {mu.visibility = View.VISIBLE}
            }
        }
    }

    override fun getItemCount(): Int = playlists.size
}
