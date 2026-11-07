package com.nerox.air

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private val tracks = listOf(
        "SoundHelix Song 1" to "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
        "SoundHelix Song 2" to "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
        "SoundHelix Song 3" to "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleView = findViewById<TextView>(R.id.currentTrack)
        val listView = findViewById<ListView>(R.id.trackList)
        val pauseButton = findViewById<Button>(R.id.pauseButton)

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tracks.map { it.first }
        )

        listView.setOnItemClickListener { _, _, position, _ ->
            val (title, url) = tracks[position]
            titleView.text = getString(R.string.now_playing, title)
            playTrack(url)
        }

        pauseButton.setOnClickListener {
            mediaPlayer?.let { player ->
                if (player.isPlaying) {
                    player.pause()
                    pauseButton.text = getString(R.string.resume)
                } else {
                    player.start()
                    pauseButton.text = getString(R.string.pause)
                }
            }
        }
    }

    private fun playTrack(url: String) {
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            setOnPreparedListener { start() }
            prepareAsync()
            setOnCompletionListener {
                findViewById<Button>(R.id.pauseButton).text = getString(R.string.pause)
            }
        }

        findViewById<Button>(R.id.pauseButton).text = getString(R.string.pause)
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}
