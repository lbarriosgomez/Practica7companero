package com.example.miniprojectquizzufc

import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.widget.ProgressBar

class QuizTimer(
    private val context: Context, // Contexto de la actividad actual
    private val progressBar: ProgressBar, // ProgressBar que queremos actualizar
    private val nextActivity: Class<*>,
    private val onTimeUpCallback: () -> Unit// La actividad a la que se cambiará cuando termine el tiempo
) {
    private var countDownTimer: CountDownTimer? = null
    private val totalTime = 30000L // 30 segundos en milisegundos
    private val interval = 100L // Intervalo de actualización cada 100ms

    // Método para iniciar el temporizador
    fun start() {
        countDownTimer = object : CountDownTimer(totalTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((millisUntilFinished.toFloat() / totalTime) * 100).toInt()
                progressBar.progress = progress // Actualiza la barra de progreso
            }

            override fun onFinish() {
                onTimeUpCallback() //
                goToNextQuestion() // Cambia a la siguiente actividad cuando termine el tiempo
            }
        }.start()
    }

    // Método para cancelar el temporizador (útil cuando el usuario responde antes de que termine el tiempo)
    fun cancel() {
        countDownTimer?.cancel()
    }

    // Método para ir a la siguiente actividad cuando termine el tiempo
    private fun goToNextQuestion() {
        val intent = Intent(context, nextActivity)
        context.startActivity(intent)
    }
}