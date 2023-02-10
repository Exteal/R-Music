package com.example.kotlinapp

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class MusicPlaylistAdapter(musicList: ArrayList<Music>) : MusicAdapter(musicList) {
    private val othersRecyclers = ArrayList<RecyclerView>()

    fun addToOtherLists(re : RecyclerView) {
        othersRecyclers.add(re)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        viewHolder.itemView.setOnClickListener { onMusicClick(it, position) }
    }

    override fun onMusicClick(it : View, position: Int) {
        othersRecyclers.forEach { re -> re.children.forEach { ch ->
            ch.setBackgroundColor(ContextCompat.getColor(it.context, R.color.black)) } }
        super.onMusicClick(it, position)
        //TODO color change -> define default color
    }
}
