package com.n1ck120.cotaobitcoin

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import java.net.URL
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONObject
import java.text.NumberFormat
import java.util.*

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

        val API_URL = "https://www.mercadobitcoin.net/api/BTC/ticker/"
        var cotacao = 0.0

        val txtcotacao = findViewById<TextView>(R.id.BitcoinVal)
        val txtQtdBitcoins = findViewById<TextView>(R.id.Result)
        val txtValor = findViewById<EditText>(R.id.editTextNumber)

        fun buscarcotacao(){
            GlobalScope.async(Dispatchers.Default){
                val resp = URL(API_URL).readText()
                cotacao = JSONObject(resp).getJSONObject("ticker").getDouble("last")
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                val cotacaoFormatada = f.format(cotacao)

                txtcotacao.setText(cotacaoFormatada)

                withContext(Main){}
            }
        }

        fun calcular() {
            if (txtValor.text.isEmpty()) {
                txtValor.error = "Preencha um valor"
                return
            }
            //valor digitado pelo usuário
            val valorDigitado = txtValor.text.toString()
                .replace(",", ".").toDouble()
            //caso não vem valor da API
            val resultado = if (cotacao > 0) valorDigitado / cotacao
            else 0.0
            //atualizando o TextView com o resultado formatado com 8 casas decimais
            txtQtdBitcoins.text = "BTC "+"%.8f".format(resultado).replace(",", ".")
        }

        buscarcotacao()

        txtValor.doAfterTextChanged {
            calcular()
        }
    }
}