package com.example.miniprojectquizzufc

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinalActivity : AppCompatActivity() {

    private val telefono = "+34645982765"
    private val correoEmpresaDestinatario = "ufcfightpass@gmail.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        MusicPlayerGG.changeMusic(this, R.raw.audio_while_quizz)
        val textViewScore = findViewById<TextView>(R.id.textViewScoreUser)
        textViewScore.text = "${PuntuacionManager.puntos}/10"
        val buttonUrl = findViewById<ImageButton>(R.id.imageButtonGoogle)
        buttonUrl.setOnClickListener { openWebPage("https://www.ufc.com/") }
        val asunto = "Solicito información para comprar entradas"
        val destinatarios = arrayOf(correoEmpresaDestinatario)
        val buttonEmail = findViewById<ImageButton>(R.id.imageButtonGmail)
        buttonEmail.setOnClickListener { composeEmail(destinatarios, asunto) }
        val buttonTelefono = findViewById<ImageButton>(R.id.imageButtonPhone)
        buttonTelefono.setOnClickListener { dialPhoneNumber(telefono) }
        val buttonCalendarioCitaWhatsapp = findViewById<ImageButton>(R.id.imageButtonWhatsapp)
        buttonCalendarioCitaWhatsapp.setOnClickListener {
            val beginTime = System.currentTimeMillis() // Tiempo actual como inicio del evento
            val endTime = beginTime + 7200000 // Evento con una duración de 2 hora (7200000 ms)

            addEvent("Reunión con Dana White", "UFC PI", beginTime, endTime)
        }

    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun composeEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun addEvent(title: String, location: String, begin: Long, end: Long) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}