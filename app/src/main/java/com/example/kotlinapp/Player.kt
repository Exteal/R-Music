package com.example.kotlinapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Timer
import kotlin.concurrent.fixedRateTimer
import kotlin.math.roundToInt

class Player(private val activity: AppCompatActivity) {
    private var sliderThread : Timer? = null

    companion object {
        var player = MediaPlayer()
        lateinit var music : Music
    }

    fun handleComponents() {
        handleButtons()
        handleSlider()
    }

    private fun handleButtons() {

        val pause : MaterialButton = activity.findViewById(R.id.pause)
        val skip : MaterialButton = activity.findViewById(R.id.skip)
        val stop : MaterialButton = activity.findViewById(R.id.stop)

        skip.setOnClickListener {
            val recycler : RecyclerView = activity.findViewById(R.id.recyclerview)

            if(selectedMusicPos < recycler.size-1) {
                val play : FloatingActionButton = activity.findViewById(R.id.playFAB)
                recycler[selectedMusicPos+1].performClick()

                play.performClick()
            }
            else {
                stop.performClick()
            }
        }

        pause.setOnClickListener {
            if(player.isPlaying) player.pause()
            else player.start()
        }

        stop.setOnClickListener {
            player.release()
            val card : MaterialCardView = activity.findViewById(R.id.playerCard)
            card.visibility = View.INVISIBLE
        }
    }

    private fun handleSlider() {
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
    fun stop() {
        player.release()
    }
    fun playMusic() {
        val description : TextView = activity.findViewById(R.id.playerDescription)
        val slider : Slider = activity.findViewById(R.id.playerSlider)

        description.text = music.name
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


}

/*
      player.setAudioAttributes(AudioAttributes.Builder()
          .setUsage(AudioAttributes.USAGE_MEDIA)
          .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
          .setLegacyStreamType(AudioManager.STREAM_MUSIC)
          .build())

      val res = resources.openRawResourceFd(music.getSound())
      if (res == null) {
        Log.d("TAG", "FD NULL")
      }
      else {
          player.setDataSource(res.fileDescriptor)
          player.prepare()
          player.start()
          //
      }*/