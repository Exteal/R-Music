package com.example.kotlinapp

val moe = Music("moeshop", "superstar", 1, listOf())
val psy = Music("psyqui", "don't you want me now", 2, listOf())
val rush = Music("rushgarcia", "don't you want me now", 3, listOf())


val edm = PlayList("radio_edm", arrayListOf(moe,moe))
val best = PlayList("the_best", arrayListOf(rush,rush,psy))
val top = PlayList("top5", arrayListOf(psy, moe, rush))

val playlists = listOf(
    edm,best,top

)

val musics = listOf(
    moe, psy, rush
)

val tags = listOf(Tag.ELECTRO, Tag.FUNK, Tag.HOUSE, Tag.JAZZ, Tag.POP)