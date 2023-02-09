package com.example.kotlinapp

val moe = Music("Moe shop", "Superstar", 1, listOf(Tag.WEEB, Tag.POP))
val psy = Music("Bizen", "If the sky clears", 2, listOf(Tag.JAZZ, Tag.FUNK))
val rush = Music("Koutak", "Rainbow after rain", 3, listOf(Tag.JAZZ, Tag.HOUSE))
val hero = Music("Skillet", "Hero", 4, listOf(Tag.ROCK, Tag.FUNK))
val garden = Music("Fragile Garden", "Primary", 5, listOf(Tag.WEEB, Tag.POP))

val edm = PlayList("radio_edm", arrayListOf(moe,moe, hero))
val best = PlayList("the_best", arrayListOf(rush,rush,psy, garden, garden, moe))
val top = PlayList("top5", arrayListOf(psy, moe, rush, garden, hero,garden, hero,garden, hero,garden, hero,garden, hero,garden, hero,moe))

val playlists = listOf(
    edm,best,top
)

val musics = listOf(
    moe, psy, rush, garden, hero
)

val tags = listOf(Tag.FUNK, Tag.HOUSE, Tag.JAZZ, Tag.POP, Tag.ROCK, Tag.WEEB)