package com.example.miniprojectquizzufc

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

object SoundEffectPlayer {

    private var soundPool: SoundPool? = null
    private var buttonSoundId: Int = 0

    fun init(context: Context) {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME) // Uso adecuado para efectos de sonido
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5) // Hasta 5 sonidos simultáneos
            .setAudioAttributes(audioAttributes)
            .build()

        // Cargar el sonido del botón (archivo debe estar en res/raw/)
        buttonSoundId = soundPool?.load(context, R.raw.click_button, 1) ?: 0
    }

    fun playButtonSound() {
        soundPool?.play(buttonSoundId, 1f, 1f, 0, 0, 1f)
    }

}