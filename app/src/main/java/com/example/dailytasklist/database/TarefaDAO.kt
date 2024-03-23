package com.example.dailytasklist.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.dailytasklist.model.Tarefa

class TarefaDAO(context: Context) : ITarefaDAO {

    /*
    Para não fazer tudo manualmente, onde criamos uma variavel, e vamos criando do zero ações para salvar, inserir, atualizar ou deletar, passamos os parametros abaixo:
   writableDatabase - readableDatabase. Esses parametros já estão configurados no SQLiteOpen Helper.

     */

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {

        val conteudos = ContentValues()
        conteudos.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)

        try {
            escrita.insert(DatabaseHelper.NOME_TABELA_TAREFAS, null, conteudos)
            Log.i("info_db", "Sucesso ao salvar tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar tarefa")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {

        try {

        }catch (e: Exception){
            return false
        }
        return true
    }

    override fun deletar(id: Int): Boolean {

        try {

        }catch (e: Exception){
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {

        val listaTarefas = mutableListOf<Tarefa>()

        return listaTarefas
    }

}