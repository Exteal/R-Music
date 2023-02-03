package com.example.kotlinapp

data class Music(val artist : String,
                 val name : String,
                 val ref : Int,
                 val tags: List<Tag>)

fun Music.getImage () : Int =
    when(this.ref) {
               1 -> R.drawable.moeshop
               2-> R.drawable.psyqui
               3-> R.drawable.rushgarcia
        else -> {throw java.lang.IllegalArgumentException()}
    }

fun Music.getSound() : Int =
    when(this.ref) {
        1 -> R.raw.superstar
        2 -> R.raw.cafe1
        3 -> R.raw.cafe4
        else -> {throw java.lang.IllegalArgumentException()}
    }
