package com.example.clculodeaposentadoria

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

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

        val sexo = findViewById<Spinner>(R.id.spinner)
        val resultado = findViewById<TextView>(R.id.result)
        val calcular = findViewById<Button>(R.id.button)
        val idade = findViewById<EditText>(R.id.idade)

        sexo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOf("Masculino", "Feminino"))

        calcular.setOnClickListener {
            val result : String
            if (idade.text.isEmpty()){
                idade.error = "Campo obrigatório"
            }else{
                if (idade.text.toString().toInt() < 0){
                    idade.error = "Valor inválido"
                }else{

                    if (sexo.selectedItem.toString() == "Masculino"){
                        result = (63 - idade.text.toString().toInt()).toString()
                    }else{
                        result = (58 - idade.text.toString().toInt()).toString()
                    }
                    if (result.toInt() <= 0){
                        resultado.text = "Sua aposentadoria já está disponivel"
                    }else{
                        if (result.toInt() == 1){
                            resultado.text = "Falta $result ano para sua aposentadoria"
                        }else{
                            resultado.text = "Faltam $result anos para sua aposentadoria"
                        }
                    }

                }
            }
        }
    }
}