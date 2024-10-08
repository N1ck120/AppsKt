package com.n1ck120.listadecompras

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
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

        val editInsert = findViewById<EditText>(R.id.editTextItem)
        val btnInsert = findViewById<Button>(R.id.buttonInsert)
        val listItens = findViewById<ListView>(R.id.listViewItem)

        val itensAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        listItens.adapter = itensAdapter

        btnInsert.setOnClickListener {
            if(editInsert.text.isBlank()){
                editInsert.error = "Campo Obrigatorio"
            }else{
                val produto = editInsert.text.toString()
                itensAdapter.add(produto)
                editInsert.text.clear()
            }
        }

        listItens.setOnItemClickListener { adapterView: AdapterView<*>, view, position: Int, id ->
            val item = itensAdapter.getItem(position)
            itensAdapter.remove(item)

        }
    }
}