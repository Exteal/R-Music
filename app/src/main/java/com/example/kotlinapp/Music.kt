package com.example.kotlinapp

import androidx.annotation.DrawableRes

data class Music(val artist : String,
                 val name : String,
                 @DrawableRes val image : Int,
                 val tags: List<Tag>)

fun Music.getImage () : Int =
    when(this.image) {
               1 -> R.drawable.moeshop
               2-> R.drawable.psyqui
               3-> R.drawable.rushgarcia
        else -> {throw java.lang.IllegalArgumentException()}
    }