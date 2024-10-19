package com.n1ck120.blocodenotas

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var preferencias: AnotacaoPreferencias? = null
    private var editAnotacao: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editAnotacao = findViewById<EditText>(R.id.blocoAnotacao)
        var preferencias = AnotacaoPreferencias(applicationContext)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            val textoRecuperado = editAnotacao.getText().toString()
            if (textoRecuperado == "") {
                Snackbar.make(view, "Digite uma anotação para ser salva",
                    Snackbar.LENGTH_LONG)
                    .show()
            } else {
                preferencias!!.salvarAnotacao(textoRecuperado)
                Snackbar.make(view, "Anotação salva com sucesso",
                    Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        //recuperar a anotacao salva
        val anotacao = preferencias!!.recuperarAnotacao()
        if (anotacao != "") {
            editAnotacao.setText(anotacao)
        }
    }
}