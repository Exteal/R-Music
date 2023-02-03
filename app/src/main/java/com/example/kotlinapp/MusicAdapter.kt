package com.example.kotlinapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
var selectedMusicPos : Int? = null
class MusicAdapter(private val dataSet: List<Music>) : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
        class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {

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

            fun changeColor(pos: Int) {
                if (selectedMusicPos == pos) {
                    itemView.setBackgroundColor(Color.RED)
                }
                else {
                    itemView.setBackgroundColor(Color.WHITE)
                }
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
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        val music = dataSet[position]
        viewHolder.bind(music)
        viewHolder.changeColor(position)

        viewHolder.itemView.setOnClickListener {


            val root = it.rootView
            val player : CardView = root.findViewById(R.id.player)
            val description : TextView = player.findViewById(R.id.Description)
            description.text = music.name

            val pos = selectedMusicPos
            if(position == selectedMusicPos) {
                selectedMusicPos = null
            }
            else {
                Player.music = music
                selectedMusicPos = position
                if (pos != null) {
                    notifyItemChanged(pos)
                }
            }
            notifyItemChanged(position)
        }
    }

    /*unhilight() {

    }*/

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
