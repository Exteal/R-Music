package com.example.kotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class PlayListAdapter(private val playlists: List<PlayList>) :
    RecyclerView.Adapter<PlayListAdapter.ViewHolder>(), Filterable {

    private val recyclers = ArrayList<RecyclerView>()
    private var filterResults = ArrayList<PlayList>(playlists)


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val playlistName : TextView
        private val musicsList : RecyclerView

        init {
            playlistName = view.findViewById(R.id.playlistName)
            musicsList = view.findViewById(R.id.playlistMusicsList)
        }

        fun bind(playlist : PlayList, recyclers : ArrayList<RecyclerView>) {
            playlistName.text = playlist.name

            val adapter = MusicPlaylistAdapter(playlist.musicList)
            adapter.addToOtherLists(musicsList)
            recyclers.forEach { val v = it.adapter as MusicPlaylistAdapter
                v.addToOtherLists(musicsList)
            }
            recyclers.forEach { adapter.addToOtherLists(it) }

            recyclers.add(musicsList)
            musicsList.adapter = adapter



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
        val playlist = filterResults[position]

        viewholder.bind(playlist, recyclers)

        viewholder.itemView.setOnClickListener {

            val mu : RecyclerView = it.findViewById(R.id.playlistMusicsList)
            when(mu.visibility) {
                View.INVISIBLE -> {}
                View.VISIBLE -> {mu.visibility = View.GONE}
                View.GONE ->  {mu.visibility = View.VISIBLE}
            }
        }
    }


    override fun getItemCount(): Int = filterResults.size
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(condition: CharSequence?): FilterResults {
                filterResults = if(condition.isNullOrBlank()) {
                       ArrayList(playlists)
                    } else {
                        val res = ArrayList<PlayList>()
                        for (playlist in playlists) {
                            if (playlist.name.contains(condition)) res.add(playlist)
                        }
                        res
                   }

                val results = FilterResults()
                results.values = filterResults
                return results

            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
               filterResults = results?.values as ArrayList<PlayList>
               notifyDataSetChanged()
            }

        }
    }
}
