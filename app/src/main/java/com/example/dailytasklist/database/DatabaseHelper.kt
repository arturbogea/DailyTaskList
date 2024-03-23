package com.example.dailytasklist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS
) {

    companion object {
        private const val VERSAO_BANCO_DADOS = 1
        private const val NOME_BANCO_DADOS = "tarefas.db"
        const val NOME_TABELA_TAREFAS = "tarefas"
        const val COLUNA_ID_TAREFAS = "id_tarefa"
        const val COLUNA_DESCRICAO = "descricao"
        const val COLUNA_DATA_CADASTRO = "data_cadastro"


    }

    override fun onCreate(db: SQLiteDatabase?) {

        val sqlTabela = "CREATE TABLE IF NOT EXISTS $NOME_TABELA_TAREFAS (" +
                "$COLUNA_ID_TAREFAS INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$COLUNA_DESCRICAO VARCHAR(70) NOT NULL," +
                "$COLUNA_DATA_CADASTRO DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");"
        try {
            db?.execSQL( sqlTabela )
            Log.i("info_db", "Sucesso ao criar a tabela v: $VERSAO_BANCO_DADOS")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar tabela ${e.message}")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}