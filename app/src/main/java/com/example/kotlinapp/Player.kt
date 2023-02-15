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

    var playingMusicPos by Delegates.notNull<Int>()
    var storedMusicPos  = -1
    var playlist : ArrayList<Music> = ArrayList()

    private var sliderThread : Timer? = null
    var player = MediaPlayer()

    //use every activity change
    fun<L : PlayerLayout> handleComponents(activity: AppCompatActivity, layoutType: Class<L>) {
            handleButtons(activity, layoutType)
            handleSlider(activity)
    }

    fun isValidPosition(position: Int) = position in (playlist.indices)
    private fun <L: PlayerLayout> handleButtons(activity: AppCompatActivity, layoutType: Class<L>) {

        val pause : MaterialButton = activity.findViewById(R.id.pause)
        val skipNext : MaterialButton = activity.findViewById(R.id.next)
        val stop : MaterialButton = activity.findViewById(R.id.stop)
        val play : FloatingActionButton = activity.findViewById(R.id.play)

        val layoutUpdater = layoutType.getConstructor(AppCompatActivity::class.java).newInstance(activity)
        layoutUpdater.setFabIcon()

        skipNext.setOnClickListener {
            if(playingMusicPos+1 in playlist.indices) {
                playingMusicPos++
                storedMusicPos =  playingMusicPos

            }
            else {
                playingMusicPos = 0
                storedMusicPos =  playingMusicPos
            }
            play.performClick()
        }

        pause.setOnClickListener {
            if (player.isPlaying)
                layoutUpdater.toPauseLayout()
            else
                layoutUpdater.fromPauseLayout()
            pause(activity)

        }

        stop.setOnClickListener {
            stop()
            layoutUpdater.onStopLayout()
        }

        play.setOnClickListener {
            when(storedMusicPos) {
                -1 -> {
                    stop()
                    layoutUpdater.onStopLayout()
                }
                else -> {
                    playMusic(activity)
                    layoutUpdater.onPlayLayout()
                }
            }
        }

    }

    fun changeLoopState() {
        player.isLooping = !player.isLooping
    }

    private fun handleSlider(activity: AppCompatActivity) {
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
        player.reset()
    }

    private fun pause(activity: AppCompatActivity) {
        if(player.isPlaying)  {
            sliderThread?.cancel()
            player.pause()
        }
        else {
            player.start()
            val slider : Slider = activity.findViewById(R.id.playerSlider)

            sliderThread = newSliderThread(slider)
        }
    }

    private fun newSliderThread(slider: Slider) : Timer =  fixedRateTimer(initialDelay = 1000, period = 1000) {
        if (slider.value + 1000 < slider.valueTo) slider.value += 1000.toFloat()
        else if(player.isLooping) {slider.value = 0f}
        else this.cancel()
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


        player.setOnPreparedListener {
            slider.valueTo = player.duration.toFloat()
            sliderThread?.cancel()
            player.start()
            slider.value = 0f

            sliderThread = newSliderThread(slider)

        }
    }
}



