package com.example.proyectodados

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodados.databinding.ActivityDadosBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Dados : AppCompatActivity() {

    private lateinit var btnatrasllamar: Button
    private lateinit var bindingDados: ActivityDadosBinding
    private var sum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDados = ActivityDadosBinding.inflate(layoutInflater)
        setContentView(bindingDados.root)

        // Recoge los datos de la configuración

        val nombreJugador = intent.getStringExtra("NOMBRE_JUGADOR") ?: ""
        val mostrarCarta = intent.getBooleanExtra("MOSTRAR_CARTA", true)
        val mostrarHistorial = intent.getBooleanExtra("MOSTRAR_HISTORIAL", false)

        // Puedes usar estos datos en tu lógica
        // Por ejemplo, mostrar el nombre del jugador en un TextView
        bindingDados.textNombre.text = "Jugador: $nombreJugador"

        initEvent()
        mostrarCarta()


        btnatrasllamar = this.findViewById(R.id.boton_atras)
        btnatrasllamar.setOnClickListener { view ->
            intent = Intent(this, Configuracion::class.java)
            startActivity(intent)
        }
    }

    private fun initEvent() {
        bindingDados.txtResultado.visibility = View.INVISIBLE
        bindingDados.imageButton.setOnClickListener {
            bindingDados.txtResultado.visibility = View.VISIBLE
            game()
        }
    }

    private fun game() {
        scheduleRun()
    }

    private fun scheduleRun() {
        val numTiradas = intent.getStringExtra("NUM_TIRADAS")?.toInt() ?: 0
        val schedulerExecutor = Executors.newSingleThreadScheduledExecutor()
        val msc = 1000
        for (i in 1..numTiradas) {
            schedulerExecutor.schedule(
                {
                    throwDadoInTime()
                },
                msc * i.toLong(), TimeUnit.MILLISECONDS
            )
        }

        schedulerExecutor.schedule({
            viewResult()
        }, msc * numTiradas .toLong(), TimeUnit.MILLISECONDS)

        schedulerExecutor.shutdown()
    }

    private fun throwDadoInTime() {
        val numDados = Array(3) { Random.nextInt(1, 7) }
        val imageViews: Array<ImageView> = arrayOf(
            bindingDados.imagviewDado1,
            bindingDados.imagviewDado2,
            bindingDados.imagviewDado3
        )

        sum = numDados.sum()
        for (i in 0 until 3) {
            selectView(imageViews[i], numDados[i])
        }
    }

    private fun selectView(imgV: ImageView, v: Int) {
        val cartaONumero = intent.getBooleanExtra("CARTA_O_NUMERO", false)
        if (cartaONumero) {
            // Lógica para mostrar números en lugar de cartas
            // Puedes personalizar esto según tus necesidades
            imgV.setImageResource(getDrawableForNumber(v))
        } else {
            // Lógica para mostrar cartas según el valor del dado
            when (v) {
                1 -> imgV.setImageResource(R.drawable.dado1)
                2 -> imgV.setImageResource(R.drawable.dado2)
                3 -> imgV.setImageResource(R.drawable.dado3)
                4 -> imgV.setImageResource(R.drawable.dado4)
                5 -> imgV.setImageResource(R.drawable.dado5)
                6 -> imgV.setImageResource(R.drawable.dado6)
            }
        }
    }

    private fun getDrawableForNumber(number: Int): Int {
        // Personaliza esta función para asignar recursos según el número
        when (number) {
            1 -> return R.drawable.carta1
            2 -> return R.drawable.carta2
            3 -> return R.drawable.carta3
            4 -> return R.drawable.carta4
            5 -> return R.drawable.carta5
            6 -> return R.drawable.carta6


        }
        return TODO("Provide the return value")
    }
    private fun viewResult() {
        bindingDados.txtResultado.text = sum.toString()
        println(sum)
    }
    private fun mostrarCarta() {
        val mostrarCarta = intent.getBooleanExtra("MOSTRAR_CARTA", true)

        if (mostrarCarta) {
            // Si se debe mostrar la carta, establece la visibilidad en VISIBLE
            bindingDados.imagviewDado1.visibility = View.VISIBLE
            bindingDados.imagviewDado2.visibility = View.VISIBLE
            bindingDados.imagviewDado3.visibility = View.VISIBLE
        } else {
            // Si no se debe mostrar la carta, establece la visibilidad en INVISIBLE
            bindingDados.imagviewDado1.visibility = View.INVISIBLE
            bindingDados.imagviewDado2.visibility = View.INVISIBLE
            bindingDados.imagviewDado3.visibility = View.INVISIBLE
        }
    }


}
