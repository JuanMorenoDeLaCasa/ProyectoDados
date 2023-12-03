package com.example.proyectodados

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var btnllamar: Button
    private lateinit var intentllamar: Intent
    private lateinit var btnalarma: Button
    private lateinit var intentalarma: Intent
    private lateinit var btncorreo: Button
    private lateinit var intentcorreo: Intent
    private lateinit var btnurl: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el clic del botón para abrir la nueva actividad
        binding.button9.setOnClickListener {
            val intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
        btnllamar = findViewById(R.id.boton_llamar)
        btnllamar.setOnClickListener { view ->
            intentllamar = Intent(this, LLamar::class.java)
            startActivity(intentllamar)
        }
        btnalarma = findViewById(R.id.boton_alarma)
        btnalarma.setOnClickListener { view ->
            intentalarma = Intent(this, Alarma::class.java)
            startActivity(intentalarma)
        }
        btncorreo = findViewById(R.id.boton_correo)
        btncorreo.setOnClickListener { view ->
            intentcorreo = Intent(this, Correo::class.java)
            startActivity(intentcorreo)
        }
        btnurl = findViewById(R.id.boton_url)
        btnurl.setOnClickListener(View.OnClickListener {
            val url = "https://www.aemet.es/es/eltiempo/prediccion/municipios/jaen-id23050"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        })
        binding.button10.setOnClickListener {
            val intent = Intent(this, Chistes::class.java)
            startActivity(intent)
        }
    }
    }

