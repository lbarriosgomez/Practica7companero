package com.example.miniprojectquizzufc

import android.content.Context
import android.media.MediaPlayer

object MusicPlayerGG {

    private var mediaPlayer: MediaPlayer? = null

    // Iniciar la música solo si aún no está sonando
    fun startMusic(context: Context, musicResId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        }
    }

    // Cambia la música actual por otra
    fun changeMusic(context: Context, newMusicResId: Int) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, newMusicResId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    // Detiene la música completamente
    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}