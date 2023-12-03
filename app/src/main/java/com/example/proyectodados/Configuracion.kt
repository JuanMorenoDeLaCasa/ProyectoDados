package com.example.proyectodados

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodados.databinding.ActivityConfiguracionBinding

class Configuracion : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView3.setOnClickListener {
            val intent = Intent(this, Dados::class.java)
            // Pasa los datos necesarios a Dados
            intent.putExtra("NUM_TIRADAS", binding.editTextText2.text.toString())
            intent.putExtra("NOMBRE_JUGADOR", binding.editTextText.text.toString())
            intent.putExtra("CARTA_O_NUMERO", binding.switch1.isChecked)
            intent.putExtra("MOSTRAR_CARTA", binding.radioButton.isChecked)
            startActivity(intent)
        }
    }
}
