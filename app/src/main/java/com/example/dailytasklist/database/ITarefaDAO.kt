package com.example.dailytasklist.database

import com.example.dailytasklist.model.Tarefa

interface ITarefaDAO {
    fun salvar( tarefa: Tarefa): Boolean
    fun atualizar( tarefa: Tarefa): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Tarefa>
}