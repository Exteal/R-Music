package com.example.kotlinapp

data class Music(val artist : String,
                 val name : String,
                 val ref : Int,
                 val tags: List<Tag>) : java.io.Serializable


