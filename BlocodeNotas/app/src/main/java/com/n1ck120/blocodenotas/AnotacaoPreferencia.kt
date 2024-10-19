package com.n1ck120.blocodenotas

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class AnotacaoPreferencias(var context: Context) {
    private val preferences: SharedPreferences
    private val NOME_ARQUIVO = "anotacao.preferencias"
    private val editor: Editor
    private val CHAVE_NOME = "nome"
    fun salvarAnotacao(anotacao: String?) {
        editor.putString(CHAVE_NOME, anotacao)
        editor.commit()
    }

    fun recuperarAnotacao(): String? {
        return preferences.getString(CHAVE_NOME, "")
    }

    init {
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0)
        editor = preferences.edit()
    }
}