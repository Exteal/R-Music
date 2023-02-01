package com.example.kotlinapp

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MusicAdapter(private val dataSet: List<Music>) :
    RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val musicImage: ImageView
        private val musicName: TextView
        private val musicArtist: TextView
        var selectedMusic : Music? = null

        init {
            // Define click listener for the ViewHolder's View
            musicName = view.findViewById(R.id.musicName)
            musicArtist = view.findViewById(R.id.musicArtist)
            musicImage= view.findViewById(R.id.musicImage)
        }

        fun bind(music: Music) {
            selectedMusic = music
            /*when(music.image) {
                1 -> musicImage.setImageResource(R.drawable.moeshop)
                2-> musicImage.setImageResource(R.drawable.psyqui)
                3-> musicImage.setImageResource(R.drawable.rushgarcia)
            }*/

            Log.d("IAMGE", "res/drawable/${music.artist}.jpg")
            //musicImage.setImageURI(Uri.fromFile(File("C:\\Users\\rh4\\AndroidStudioProjects\\KotlinApp\\app\\src\\main\\res\\drawable\\${music.artist}.jpg")))
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
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val music = dataSet[position]
        viewHolder.bind(music)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
