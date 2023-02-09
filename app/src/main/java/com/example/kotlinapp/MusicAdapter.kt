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
import androidx.recyclerview.widget.RecyclerView


open class MusicAdapter(private val dataSet: List<Music>) :
                RecyclerView.Adapter<MusicAdapter.ViewHolder>(), Filterable {

    private val views = ArrayList<View>()
    var filterResults : ArrayList<Music>

    init  {
        filterResults = ArrayList(dataSet)
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
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

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.music_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)



    private fun toDefaultColor() {
        views.forEach { v -> v.setBackgroundColor(Color.WHITE) }
    }
    open fun onMusicClick(it : View, position: Int) {
        if(Player.playlist == dataSet && Player.storedMusicPos == position) {
            Player.storedMusicPos =-1
            it.setBackgroundColor(Color.WHITE)
        }

        else {
            when(Player.storedMusicPos) {
                -1 -> {}
                else -> toDefaultColor()
            }

            it.setBackgroundColor(Color.RED)
            Player.playlist = ArrayList(dataSet)
            Player.storedMusicPos = position
        }


    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val music = filterResults[position]
        viewHolder.bind(music)
        views.add(viewHolder.itemView)

        viewHolder.itemView.setOnClickListener {
            onMusicClick(it, position)
        }

       viewHolder.itemView.setOnLongClickListener {
            val intent = Intent(it.context, MusicActivity::class.java)
            intent.putExtra("playlist", arrayListOf(dataSet))
            intent.putExtra("music", music)
            it.context.startActivity(intent)
            true

       }
    }

    // Return the size of your dataset (invoked by the layout manager)
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
