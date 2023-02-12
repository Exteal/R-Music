package com.example.kotlinapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


open class MusicAdapter(private val dataSet: List<Music>) :
                RecyclerView.Adapter<MusicAdapter.ViewHolder>(), Filterable {

    private val views = ArrayList<View>()
    var filterResults : ArrayList<Music>

    init  {
        filterResults = ArrayList(dataSet)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val musicImage: ImageView
        private val musicName: TextView
        private val musicArtist: TextView


        init {
            // Define click listener for the ViewHolder's View
            musicName = view.findViewById(R.id.musicName)
            musicArtist = view.findViewById(R.id.musicArtist)
            musicImage= view.findViewById(R.id.musicImage)
        }


        fun bind(music: Music) {
            musicImage.setImageResource(music.getImage())
            musicName.text = music.name
            musicArtist.text = music.artist
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_music, viewGroup, false)

        return ViewHolder(view)
    }




    private fun itemsToDefaultColor() {
        views.forEach { v ->
            v.setBackgroundColor(ContextCompat.getColor(v.context, R.color.background))
        }
    }

    open fun onMusicClick(it : View, position: Int) {
        if(Player.playlist == dataSet && Player.storedMusicPos == position) {
            Player.storedMusicPos =-1

            it.setBackgroundColor(ContextCompat.getColor(it.context, R.color.background))
        }

        else {
            when(Player.storedMusicPos) {
                -1 -> {}
                else -> itemsToDefaultColor()
            }

            it.setBackgroundColor(Color.RED)
            Player.playlist = filterResults
            Player.storedMusicPos = position
        }


    }
    open fun onLongMusicClick(it: View, position: Int) {
        val music = filterResults[position]
        val intent = Intent(it.context, MusicActivity::class.java)
        val playlist =  PlayList("Home", ArrayList(dataSet))
        intent.putExtra("playlist", playlist)
        intent.putExtra("music", music)
        Player.playlist = filterResults
        Player.storedMusicPos = filterResults.indexOf(music)
        it.context.startActivity(intent)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val music = filterResults[position]
        viewHolder.bind(music)
        views.add(viewHolder.itemView)

        viewHolder.itemView.setOnClickListener {
            onMusicClick(it, position)
        }

       viewHolder.itemView.setOnLongClickListener {
           onLongMusicClick(it, position)
           true
       }
    }


    override fun getItemCount() = filterResults.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(condition: CharSequence?): FilterResults {
                filterResults = if(condition.isNullOrEmpty()) {
                    ArrayList(dataSet)
                } else {
                    val res = ArrayList<Music>()
                        for(music in dataSet) {
                            if(music.name.contains(condition, ignoreCase = true)
                                || music.artist.contains(condition,ignoreCase = true)
                                || music.tags.any { it.name.contains(condition, ignoreCase = true) })
                                res.add(music)
                        }
                    res
                }

                val results = FilterResults()
                results.values = filterResults
                return results
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                filterResults = results?.values as ArrayList<Music>
                notifyDataSetChanged()
            }

        }
    }

}
