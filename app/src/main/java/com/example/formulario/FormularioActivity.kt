package com.example.formulario

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.DatePickerDialog
import java.util.Calendar
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormularioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)
        val nombre = findViewById<EditText>(R.id.nombre)
        val edad = findViewById<EditText>(R.id.edad)
        val email = findViewById<EditText>(R.id.email)
        val fechaNacimiento = findViewById<EditText>(R.id.fechaNacimiento)
        fechaNacimiento.setOnClickListener {
            val calendario = Calendar.getInstance()

            val año = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                {_, añoSeleccionado, mesSeleccionado, diaSeleccionado ->

                    val fecha = "$diaSeleccionado/${mesSeleccionado+1}/$añoSeleccionado"
                    fechaNacimiento.setText(fecha)
                },
                año,
                mes,
                dia
            )
            datePicker.show()
        }
        val telefono = findViewById<EditText>(R.id.telefono)
        val direccion = findViewById<EditText>(R.id.direccion)
        val botonLimpiar = findViewById<Button>(R.id.limpiar)
        botonLimpiar.setOnClickListener {
            nombre.text.clear()
            edad.text.clear()
            email.text.clear()
            fechaNacimiento.text.clear()
            telefono.text.clear()
            direccion.text.clear()
        }

        val botonEnviar = findViewById<Button>(R.id.enviar)
        botonEnviar.setOnClickListener {
            val textoNombre = nombre.text.toString()
            val textoEdad = edad.text.toString()
            val textoEmail = email.text.toString()
            val textoFecha = fechaNacimiento.text.toString()
            val textoTelefono = telefono.text.toString()
            val textoDireccion = direccion.text.toString()

            Toast.makeText(
                this,
                "Nombre: $textoNombre\nEdad: $textoEdad\nEmail: $textoEmail\nFecha de nacimiento: $textoFecha\nTelefono: $textoTelefono\n Direccion: $textoDireccion",
                Toast.LENGTH_LONG
            ).show()

            AlertDialog.Builder(this)
                .setTitle("Enviado")
                .setMessage("Su nombre es $textoNombre" +
                        "Su edad es $textoEdad \n" +
                        "Su Correo Electronico es: $textoEmail\n" +
                        "Su Fecha de Nacimiento es: $textoFecha\n" +
                        "Su telefono es: $textoTelefono\n" +
                        "Su direccion es: $textoDireccion\n")

                .setPositiveButton("Aceptar", null)
                .show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}