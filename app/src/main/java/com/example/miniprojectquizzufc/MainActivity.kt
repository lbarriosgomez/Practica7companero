package com.example.miniprojectquizzufc

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.miniprojectquizzufc.MusicPlayerGG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            Thread.sleep(4800)
            false
        }
        setContentView(R.layout.activity_main)
        SoundEffectPlayer.init(this)
        // Iniciar la música del Splash Screen (audio de 4.8 segundos)
        MusicPlayerGG.startMusic(this, R.raw.bruce_buffer_its_timefivesec)

        // Detener la música después de 4.8 segundos, cuando termine el Splash Screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Detener la música del Splash Screen
            MusicPlayerGG.stopMusic()
            // Iniciar la música de introducción
            MusicPlayerGG.startMusic(this, R.raw.audio_ufc_intro)
        }, 4800)
    }

    fun goToActivity1(view: View) {
        PuntuacionManager.resetPuntuacion()
        MusicPlayerGG.changeMusic(this, R.raw.audio_while_quizz_gg)
        val intent = Intent(this, ActivityQuestion1::class.java)
        startActivity(intent)
        finish()
    }

}