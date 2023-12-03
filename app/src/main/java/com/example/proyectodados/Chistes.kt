package com.example.proyectodados

import android.content.Intent
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectodados.databinding.ActivityChistesBinding
import java.util.Locale



class Chistes : AppCompatActivity() {


    private lateinit var binding : ActivityChistesBinding
    private lateinit var textToSpeech: TextToSpeech  //descriptor de voz
    private val TOUCH_MAX_TIME = 500 // en milisegundos
    private var touchLastTime: Long = 0  //para saber el tiempo entre toque.
    private var touchNumber = 0   //numero de toques dado (por si acaso). De momento no nos hace falta.
    private lateinit var handler: Handler
    val MYTAG = "LOGCAT"  //para mirar logs

    private val chistesArray = arrayOf(
        "Chiste 1",
        "Chiste 2",
        "Chiste 3",
        "Chiste 4",
        "Chiste 5",
        "Chiste 6",
        "Chiste 7",
        "Chiste 8",
        "Chiste 9",
        "Chiste 10",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChistesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureTextToSpeech()  //configuramos nuestro textToSpeech
        initHander()    //lanzaremos un hilo para el progressBar. No es necesario un hilo.
        initEvent()     //Implementación del botón.



    }



    private fun initHander() {
        handler = Handler(Looper.getMainLooper())  //queremos que el tema de la IU, la llevemos al hilo principal.
        binding.progressBar.visibility = View.VISIBLE  //hacemos visible el progress
        binding.btnExample.visibility = View.GONE  //ocultamos el botón.

        Thread{
            Thread.sleep(3000)
            handler.post{
                binding.progressBar.visibility = View.GONE  //ocultamos el progress
                val description = getString(R.string.describe).toString()
                speakMeDescription(description)  //que nos comente de qué va esto...
                Thread.sleep(4000)
                Log.i(MYTAG,"Se ejecuta correctamente el hilo")
                binding.btnExample.visibility = View.VISIBLE

            }
        }.start()
    }



    private fun configureTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
            if(it != TextToSpeech.ERROR){
                textToSpeech.language = Locale.getDefault()
                // textToSpeech.setSpeechRate(1.0f)
                Log.i(MYTAG,"Sin problemas en la configuración TextToSpeech")
            }else{
                Log.i(MYTAG,"Error en la configuración TextToSpeech")
            }
        })
    }

    /*
    Como he dicho esta mañana, System.currentTimeMillis() devuelve el tiempo actual
    en milisegundos. Por cada click el touchLastTime se vuelve a actualizar, para hacer
    la diferencia de tiempos y comprobar si es menor de 500 msg.
     */
    private fun initEvent() {
        val chistesArray = resources.getStringArray(R.array.chistes_array)

        binding.btnExample.setOnClickListener {
            // Sacamos el tiempo actual
            val currentTime = System.currentTimeMillis()

            // Comprobamos si el margen entre pulsación da lugar a una doble pulsación.
            if (currentTime - touchLastTime < TOUCH_MAX_TIME) {
                executorDoubleTouch(chistesArray)  // Lanzamos el chiste aleatorio.
            } else {
                Log.i(MYTAG, "Hemos pulsado 1 vez.")
                // Describimos el botón para 1 sola pulsación.
                speakMeDescription("Botón para escuchar un chiste")
            }

            touchLastTime = currentTime
        }
    }

    private fun executorDoubleTouch(chistesArray: Array<String>) {
        val chisteAleatorio = chistesArray.random()
        speakMeDescription(chisteAleatorio)
    }


    //Habla
    private fun speakMeDescription(s: String) {
        Log.i(MYTAG,"Intenta hablar")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun executorDoubleTouch(chiste: String) {
        speakMeDescription(chiste)
        // Toast.makeText(this,"doble pulsacion-> Ejecuto la acción",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        //Si hemos inicializado la propiedad textToSpeech, es porque existe.
        if (::textToSpeech.isInitialized){
            textToSpeech.stop()
            textToSpeech.shutdown()

        }
        super.onDestroy()
    }

}