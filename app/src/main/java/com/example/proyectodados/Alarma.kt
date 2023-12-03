package com.example.proyectodados


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button


class Alarma : AppCompatActivity() {

    private lateinit var btnatrasalarma: Button
    private lateinit var btnalarma1: Button
    private lateinit var btnalarma2: Button
    private lateinit var btnalarma3: Button
    private lateinit var btnalarma4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarma)

        btnatrasalarma = this.findViewById(R.id.boton_atras_alarma)
        btnatrasalarma.setOnClickListener { view ->
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnalarma1 = this.findViewById(R.id.boton1)
        btnalarma1.setOnClickListener {
            val currentTime = android.icu.util.Calendar.getInstance()
            currentTime.add(android.icu.util.Calendar.MINUTE, 1)

            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma")
                putExtra(
                    AlarmClock.EXTRA_HOUR,
                    currentTime.get(android.icu.util.Calendar.HOUR_OF_DAY)
                )
                putExtra(
                    AlarmClock.EXTRA_MINUTES,
                    currentTime.get(android.icu.util.Calendar.MINUTE)
                )
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

        }
        btnalarma2 = this.findViewById(R.id.boton2)
        btnalarma2.setOnClickListener {
            val currentTime = android.icu.util.Calendar.getInstance()
            currentTime.add(android.icu.util.Calendar.MINUTE, 2)

            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma")
                putExtra(
                    AlarmClock.EXTRA_HOUR,
                    currentTime.get(android.icu.util.Calendar.HOUR_OF_DAY)
                )
                putExtra(
                    AlarmClock.EXTRA_MINUTES,
                    currentTime.get(android.icu.util.Calendar.MINUTE)
                )
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

        }
        btnalarma3 = this.findViewById(R.id.boton3)
        btnalarma3.setOnClickListener {
            val currentTime = android.icu.util.Calendar.getInstance()
            currentTime.add(android.icu.util.Calendar.MINUTE, 5)

            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma")
                putExtra(
                    AlarmClock.EXTRA_HOUR,
                    currentTime.get(android.icu.util.Calendar.HOUR_OF_DAY)
                )
                putExtra(
                    AlarmClock.EXTRA_MINUTES,
                    currentTime.get(android.icu.util.Calendar.MINUTE)
                )
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

        }
        btnalarma4 = this.findViewById(R.id.boton4)
        btnalarma4.setOnClickListener {
            val currentTime = android.icu.util.Calendar.getInstance()
            currentTime.add(android.icu.util.Calendar.MINUTE, 10)

            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(
                    AlarmClock.EXTRA_HOUR,
                    currentTime.get(android.icu.util.Calendar.HOUR_OF_DAY)
                )
                putExtra(
                    AlarmClock.EXTRA_MINUTES,
                    currentTime.get(android.icu.util.Calendar.MINUTE)
                )
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

        }
    }
}




