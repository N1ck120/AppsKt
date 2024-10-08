package com.n1ck120.conversordetemperatura

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

        val temp = findViewById<EditText>(R.id.temp)
        val button = findViewById<Button>(R.id.button)
        val result = findViewById<TextView>(R.id.result)

        button.setOnClickListener {
            if (temp.text.isEmpty()){
                temp.error = "Campo Obrigatorio"
            }else{
                val resultado : Double
                resultado = ((9 * temp.text.toString().toDouble() + 160) / 5)
                result.text = "${temp.text} C° é: $resultado F°"
            }
        }
    }
}