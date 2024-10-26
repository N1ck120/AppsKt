package com.n1ck120.sqlite

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        try {
            val bd = openOrCreateDatabase("a", MODE_PRIVATE, null)

            bd.execSQL("CREATE TABLE IF NOT EXISTS produto (nome VARCHAR, preco INT(3), quant INT(3), lote INT(4), tipo VARCHAR)")

            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Mouse', 12, 4, 35, 'eletronicos')")
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Teclado', 25, 10, 12, 'eletronicos')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Monitor', 200, 5, 45, 'eletronicos')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Impressora', 150, 2, 50, 'eletronicos')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Cabo HDMI', 15, 20, 30, 'acessorios')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Webcam', 80, 15, 10, 'eletronicos')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Fone de Ouvido', 40, 25, 22, 'eletronicos')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Microfone', 60, 8, 40, 'eletronicos')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Pendrive 32GB', 25, 30, 33, 'acessorios')");
            bd.execSQL("INSERT INTO produto (nome, preco, quant, lote, tipo) VALUES ('Bateria Externa', 100, 12, 60, 'acessorios')");


            val cursor = bd.rawQuery("SELECT nome, preco, quant, lote, tipo FROM produto", null)

            val indiceNome = cursor!!.getColumnIndex("nome")
            val indicePreco = cursor.getColumnIndex("preco")
            val indiceQuant = cursor.getColumnIndex("quant")
            val indiceLote = cursor.getColumnIndex("lote")
            val indiceTipo = cursor.getColumnIndex("tipo")
            cursor.moveToFirst()
            while (cursor != null){
                Log.i("RESULTADO = NOME: ", cursor.getString(indiceNome))
                Log.i("RESULTADO = PRECO: ", cursor.getString(indicePreco))
                Log.i("RESULTADO = QUANT: ", cursor.getString(indiceQuant))
                Log.i("RESULTADO = LOTE: ", cursor.getString(indiceLote))
                Log.i("RESULTADO = TIPO: ", cursor.getString(indiceTipo))
                cursor.moveToNext()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}