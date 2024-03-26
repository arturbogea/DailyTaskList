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

        val args = arrayOf(tarefa.idTarefa.toString())
        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)

        try {
            escrita.update(DatabaseHelper.NOME_TABELA_TAREFAS, conteudo, "${DatabaseHelper.COLUNA_ID_TAREFAS} = ?", args)
            Log.i("info_db", "Sucesso ao atualizar tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao atualizar tarefa")
            return false
        }
        return true

    }

    override fun deletar(idTarefas: Int): Boolean {

        val args = arrayOf(idTarefas.toString())

        try {
            escrita.delete(DatabaseHelper.NOME_TABELA_TAREFAS, "${DatabaseHelper.COLUNA_ID_TAREFAS} = ?", args)
            Log.i("info_db", "Sucesso ao remover tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao remover tarefa")
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {

        val listaTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT ${DatabaseHelper.COLUNA_ID_TAREFAS}, ${DatabaseHelper.COLUNA_DESCRICAO}, strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.COLUNA_DATA_CADASTRO}) ${DatabaseHelper.COLUNA_DATA_CADASTRO}  from ${DatabaseHelper.NOME_TABELA_TAREFAS}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceID = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID_TAREFAS)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.COLUNA_DESCRICAO)
        val indiceData = cursor.getColumnIndex(DatabaseHelper.COLUNA_DATA_CADASTRO)


        while (cursor!= null && cursor.moveToNext()){
            val idTarefa = cursor.getInt(indiceID)
            val descricao = cursor.getString(indiceDescricao)
            val data = cursor.getString(indiceData)

            listaTarefas.add(Tarefa(idTarefa,descricao,data))
        }

        return listaTarefas
    }

}


