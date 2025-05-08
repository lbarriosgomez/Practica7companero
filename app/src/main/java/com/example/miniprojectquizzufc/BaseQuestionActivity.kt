package com.example.miniprojectquizzufc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


abstract class BaseQuestionActivity: AppCompatActivity() {

    private lateinit var quizTimer: QuizTimer
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonNext: Button
    private var editTextRespuesta: EditText? = null // Opcional, si la pregunta tiene input de texto
    private var radioGroup: RadioGroup? = null // Opcional, para preguntas con opciones de selección única
    private var checkBox1: CheckBox? = null
    private var checkBox2: CheckBox? = null
    private var checkBox3: CheckBox? = null
    private var checkBox4: CheckBox? = null

    private lateinit var mensajesCorrecto: List<String>
    private lateinit var mensajesIncorrecto: List<String>
    private lateinit var mensajesSinResponder: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoundEffectPlayer.init(this)
        setContentView(getLayoutResource()) // Usa el layout de la actividad hija

        progressBar = findViewById(R.id.progressBar)
        buttonNext = findViewById(R.id.buttonNextQuestion)
        editTextRespuesta = findViewById(R.id.editTextRespuesta)
        radioGroup = findViewById(R.id.radio_group)
        checkBox1 = findViewById(R.id.checkbox1)
        checkBox2 = findViewById(R.id.checkbox2)
        checkBox3 = findViewById(R.id.checkbox3)
        checkBox4 = findViewById(R.id.checkbox4)

        // Iniciar el temporizador
        quizTimer = QuizTimer(this, progressBar, getNextActivityClass(), ::onTimeUp)
        quizTimer.start()

        mensajesCorrecto = listOf(
            getString(R.string.toastCorrectAnswer),
            "You're on fire! 🔥",
            "Outstanding performance! 🏆"
        )

        mensajesIncorrecto = listOf(
            getString(R.string.toastWrongAnswer),
            "Not quite, keep going! 🚀",
            "Almost there, try again! 💡"
        )

        mensajesSinResponder = listOf(
            getString(R.string.toastNoAnswer),
            "Time’s up! ⏰",
            "Don't leave it blank! ❌"
        )

        // Configurar botón para validar antes de avanzar
        buttonNext.setOnClickListener {
            SoundEffectPlayer.playButtonSound() //Reproducir sonido al presionar el botón
            if (isAnswerValid()) {
                val isCorrect = isCorrectAnswer()
                showMotivationalMessage(isCorrect, false)
                if (isCorrectAnswer()) {
                    PuntuacionManager.puntos += 1 //Incrementamos la puntuación si es correcta
                }
                goToNextQuestion()
            } else {
                //Si no escribió nada o no seleccionó opción, mostrar mensaje
                showValidationError()
            }
        }
    }

    private fun goToNextQuestion() {
        quizTimer.cancel()
        val intent = Intent(this, getNextActivityClass())
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        quizTimer.cancel()
    }

    // Validar si el usuario ha ingresado texto antes de avanzar (excepto si se acaba el tiempo)
    private fun isAnswerValid(): Boolean {
        return when {
            editTextRespuesta != null -> {
                //Si la pregunta usa EditText, validar que no esté vacío ni solo espacios
                val userInput = editTextRespuesta?.text?.toString()?.trim()
                !userInput.isNullOrEmpty()
            }
            radioGroup != null -> {
                //Si la pregunta usa RadioButtons, verificar que haya una opción seleccionada
                radioGroup?.checkedRadioButtonId != -1
            }
            areCheckBoxesPresent() -> {
                areAnyCheckBoxesChecked() //Verificar que haya al menos un checkbox marcado
            }
            else -> true //Si no hay EditText ni RadioGroup, permitir avanzar (caso raro)
        }
    }

    //Verifica si hay CheckBoxes en la pregunta actual
    private fun areCheckBoxesPresent(): Boolean {
        return checkBox1 != null && checkBox2 != null && checkBox3 != null && checkBox4 != null
    }

    //Verifica si al menos un CheckBox está marcado
    private fun areAnyCheckBoxesChecked(): Boolean {
        return checkBox1?.isChecked == true || checkBox2?.isChecked == true ||
                checkBox3?.isChecked == true || checkBox4?.isChecked == true
    }

    //Mostrar mensaje si el usuario intenta avanzar sin responder
    private fun showValidationError() {
        when {
            editTextRespuesta != null -> {
                Toast.makeText(this, "Debes ingresar una respuesta antes de continuar", Toast.LENGTH_SHORT).show()
            }
            radioGroup != null -> {
                Toast.makeText(this, "Debes seleccionar una opción antes de continuar", Toast.LENGTH_SHORT).show()
            }
            areCheckBoxesPresent() -> {
                Toast.makeText(this, "Debes marcar al menos una opción", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Verificar si la respuesta es correcta
    fun isCorrectAnswer(): Boolean {
        return when {
            editTextRespuesta != null -> {
                val userInput = editTextRespuesta?.text?.toString()?.trim() ?: ""
                getCorrectAnswers().any { it.equals(userInput, ignoreCase = true) }
            }
            radioGroup != null -> {
                val selectedRadioButtonId = radioGroup?.checkedRadioButtonId ?: -1
                if (selectedRadioButtonId != -1) {
                    val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                    val selectedAnswer = selectedRadioButton.text.toString()
                    getCorrectAnswers().any { it.equals(selectedAnswer, ignoreCase = true) }
                } else {
                    false
                }
            }
            areCheckBoxesPresent() -> {
                checkBox1?.isChecked == true && checkBox2?.isChecked == true &&
                        checkBox3?.isChecked == true && checkBox4?.isChecked == true
            }
            else -> false
        }
    }

    // Función que muestra el mensaje de feedback al usuario
    private fun showMotivationalMessage(isCorrect: Boolean, isTimeout: Boolean) {
        val message = when {
            isTimeout -> mensajesSinResponder.random() // ⏳ Mensaje aleatorio si no responde
            isCorrect -> mensajesCorrecto.random() // ✅ Mensaje aleatorio si responde bien
            else -> mensajesIncorrecto.random() // ❌ Mensaje aleatorio si responde mal
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onTimeUp() {
        showMotivationalMessage(false, true) // ⏳ Mostrar mensaje de "Wake up!"
        goToNextQuestion() // Pasar a la siguiente pregunta automáticamente
    }

    // Métodos abstractos que las actividades hijas deben implementar
    abstract fun getLayoutResource(): Int
    abstract fun getNextActivityClass(): Class<*>
    abstract fun getCorrectAnswers(): List<String> // Cada actividad define sus respuestas correctas

}