package com.example.kotlinapp

data class PlayList (val name : String, val musicList : ArrayList<Music>) : java.io.Serializable {
    val tagsList = ArrayList<String>()
}

