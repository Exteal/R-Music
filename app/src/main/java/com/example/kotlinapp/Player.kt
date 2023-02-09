package com.example.kotlinapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Timer
import kotlin.concurrent.fixedRateTimer
import kotlin.math.roundToInt
import kotlin.properties.Delegates

object Player {

    private var playingMusicPos by Delegates.notNull<Int>()
    var storedMusicPos  = -1
    var playlist : ArrayList<Music> = ArrayList()

    private var sliderThread : Timer? = null
    private var player = MediaPlayer()

    //use every activity change
    fun handleComponents(activity: AppCompatActivity, usesCardLayout : Boolean) {
            handleButtons(activity, usesCardLayout)
            handleSlider(activity, usesCardLayout)
    }

    private fun handleButtons(activity: AppCompatActivity, usesCardLayout: Boolean) {

        val pause : MaterialButton = activity.findViewById(R.id.pause)
        val skip : MaterialButton = activity.findViewById(R.id.skip)
        val stop : MaterialButton = activity.findViewById(R.id.stop)
        val fab : FloatingActionButton = activity.findViewById(R.id.playFAB)

        if(usesCardLayout) PlayerLayout.updateFabLayout(activity)

        skip.setOnClickListener {
            if(playingMusicPos+1 in playlist.indices) {
                playingMusicPos++
                storedMusicPos =  playingMusicPos
                fab.performClick()
            }
            else {
                stop.performClick()
            }
        }

        pause.setOnClickListener {
            pause()
        }

        stop.setOnClickListener {
            stop()
            if(usesCardLayout) PlayerLayout.onStopLayout(activity)
        }

        fab.setOnClickListener {
            when(storedMusicPos) {
                -1 -> {
                    stop()
                    if(usesCardLayout) PlayerLayout.onStopLayout(activity)
                }
                else -> {
                    playMusic(activity)
                    if(usesCardLayout) PlayerLayout.onPlayLayout(activity)
                }
            }
        }

    }

    private fun handleSlider(activity: AppCompatActivity, cardPlayerLayout: Boolean) {
        val slider : Slider = activity.findViewById(R.id.playerSlider)
        slider.setLabelFormatter { value ->
            val minFormat  = DecimalFormat("#")
            minFormat.roundingMode = RoundingMode.DOWN

            "${minFormat.format(value/(1000*60))} : ${String.format("%02d",((value/1000)%60).roundToInt())}"
        }

        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {

            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
                player.seekTo(slider.value.toInt())
            }
        })
    }

    private fun stop() {
        player.release()
    }

    private fun pause() {
        if(player.isPlaying) player.pause()
        else player.start()
    }

    private fun playMusic(activity: AppCompatActivity) {

        playingMusicPos = storedMusicPos

        val slider : Slider = activity.findViewById(R.id.playerSlider)
        val music = playlist[storedMusicPos]



        player.release()  //TODO -> player.reset()
        player = MediaPlayer.create(activity, music.getSound())

        player.setOnCompletionListener {
            sliderThread?.cancel()
            sliderThread = null
        }

        // val uir = "/storage/emulated/0/Android/media/Burn My Dread.mp3"
        // val ff = File(uir)
        // Log.d("UIR", ff.canRead().toString())
        // player = MediaPlayer.create(activity, ff.toUri())

        player.setOnPreparedListener {
            slider.valueTo = player.duration.toFloat()
            sliderThread?.cancel()
            slider.value = 0f

            player.start()

            sliderThread = fixedRateTimer(initialDelay = 1000, period = 1000) {
                if(slider.value+1000 < slider.valueTo) slider.value+=1000.toFloat()
                else this.cancel()
            }
        }
    }

    //card layout management


}
