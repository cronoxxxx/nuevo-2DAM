package com.example.primerprograma

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primerprograma.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() //hace que se vea toda la pantalla
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }


        binding.btnSubmit.setOnClickListener {
            checkValue()
        }


    }



    fun checkValue() {
        if(binding.etName.text.isNotEmpty() && binding.etName.text.isNotBlank()) {
            // Crea un intent para pasar el nombre a la siguiente pantalla.
            val intent = Intent(this, ResultActivity::class.java)
            // Guarda el nombre del usuario en el intent.
            intent.putExtra("INTENT_NAME", binding.etName.text.toString())
            // Inicia la siguiente pantalla.
            startActivity(intent)
        } else {
            showErrorName()
        }
    }


    fun showErrorName() {
        Toast.makeText(this,"El nombre del usuario no puede ser vacio", Toast.LENGTH_SHORT).show()
    }
}