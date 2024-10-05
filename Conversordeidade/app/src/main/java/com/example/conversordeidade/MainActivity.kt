package com.example.conversordeidade

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val anos = findViewById<EditText>(R.id.editTextAnos)
        val meses = findViewById<EditText>(R.id.editTextMeses)
        val dias = findViewById<EditText>(R.id.editTextDias)
        val resultado = findViewById<TextView>(R.id.textViewResult)
        val calcular = findViewById<Button>(R.id.button)

        calcular.setOnClickListener {
            if (anos.text.isEmpty()){
                anos.error = "Campo obrigatório"
            } else if (anos.text.toString().toInt() < 0){
                anos.error = "Valor inválido"
            }else if (meses.text.isEmpty()){
                meses.error = "Campo obrigatório"
            }else if (meses.text.toString().toInt() < 0){
                meses.error = "Valor inválido"
            }else if (dias.text.isEmpty()){
                dias.error = "Campo obrigatório"
            }else if (dias.text.toString().toInt() < 0){
                dias.error = "Valor inválido"
            }else{
                val result : Int
                result = (anos.text.toString().toInt() * 360) + (meses.text.toString().toInt() * 30) + dias.text.toString().toInt()
                resultado.text = "Sua idade total em dias é $result"
            }
        }
    }
}