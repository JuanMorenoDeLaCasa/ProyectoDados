package com.example.proyectodados


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

private lateinit var btnatrascorreo: Button
private lateinit var btnenviarcorreo: Button
class Correo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correo)

        btnatrascorreo = this.findViewById(R.id.boton_atras_correo1)
        btnatrascorreo.setOnClickListener { view ->
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val editTextDestinatario = findViewById<EditText>(R.id.editTextDestinatario1)
        val editTextAsunto = findViewById<EditText>(R.id.editTextAsunto1)
        val editTextCuerpo = findViewById<EditText>(R.id.editTextCuerpo1)
        val btnenviarcorreo = findViewById<Button>(R.id.boton_nuevocorreo1)

        btnenviarcorreo.setOnClickListener {
            val destinatario = editTextDestinatario.text.toString()
            val asunto = editTextAsunto.text.toString()
            val cuerpo = editTextCuerpo.text.toString()

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
            intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
            intent.putExtra(Intent.EXTRA_TEXT, cuerpo)

            startActivity(Intent.createChooser(intent, "Enviar Correo"))
        }
    }
}
