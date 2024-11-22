package com.n1ck120.cotao

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.text.NumberFormat
import java.util.Locale

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

        val API_URL = "https://api.hgbrasil.com/finance"
        var cotacao = 0.0

        val txtcotacao = findViewById<TextView>(R.id.BitcoinVal)
        val txtQtdBitcoins = findViewById<TextView>(R.id.Result)
        val txtValor = findViewById<EditText>(R.id.editTextNumber)
        val moeda = findViewById<TextView>(R.id.textView5)

        val currency = findViewById<Spinner>(R.id.spinner2)

        currency.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOf("BTC", "USD", "EUR"))

        fun buscarcotacao(){
            GlobalScope.async(Dispatchers.Default){
                val resp = URL(API_URL).readText()
                cotacao = JSONObject(resp).getJSONObject("results").getJSONObject("currencies").getJSONObject(currency.selectedItem.toString()).getDouble("sell")
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                val cotacaoFormatada = f.format(cotacao)

                txtcotacao.setText(cotacaoFormatada)

                withContext(Main){}
            }
        }

        fun calcular() {
            if (txtValor.text.isEmpty() || txtValor.text.toString() == ".") {
                txtValor.error = "Preencha um valor"
                return
            }
            //valor digitado pelo usuário
            val valorDigitado = txtValor.text.toString()
                .replace(",", ".").toDouble()
            //caso não vem valor da API
            val resultado = if (cotacao > 0) valorDigitado / cotacao
            else 0.0

            when (currency.selectedItem.toString()) {
                "USD" -> {
                    txtQtdBitcoins.text = currency.selectedItem.toString() + " %.2f".format(resultado).replace(",", ".")
                }
                "BTC" -> {
                    txtQtdBitcoins.text = currency.selectedItem.toString() + " %.8f".format(resultado).replace(",", ".")
                }
                "EUR" -> {
                    txtQtdBitcoins.text = currency.selectedItem.toString() + " %.2f".format(resultado).replace(",", ".")
                }

            }

        }

        currency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                buscarcotacao()
                calcular()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {

            }
        }

        txtValor.doAfterTextChanged {
            buscarcotacao()
            calcular()
        }
    }
}