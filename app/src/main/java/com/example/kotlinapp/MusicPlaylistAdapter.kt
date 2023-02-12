package com.example.kotlinapp

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

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
            ch.setBackgroundColor(ContextCompat.getColor(it.context, R.color.background)) }
        }
        super.onMusicClick(it, position)
    }

    override fun onLongMusicClick(it : View, position: Int) {
        val intent = Intent(it.context, MusicActivity::class.java)

        Player.storedMusicPos = position
        Player.playlist = filterResults


        val playlistView  = (it.parent.parent as ViewGroup).children
        val playlistName = playlistView.elementAt(0) as MaterialTextView

        val playlist =  PlayList(playlistName.text.toString(), filterResults)

        intent.putExtra("playlist", playlist)
        intent.putExtra("music", filterResults[position])
        it.context.startActivity(intent)
    }

}
