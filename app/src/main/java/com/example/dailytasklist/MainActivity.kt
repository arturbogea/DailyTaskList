package com.example.dailytasklist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dailytasklist.database.TarefaDAO
import com.example.dailytasklist.databinding.ActivityMainBinding
import com.example.dailytasklist.model.Tarefa


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        telaAddDados()



    }

    private fun telaAddDados() {
        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        //Quando eu coloco esse codigo abaixo, o app não abre mais. Na classe TarefaDAO, está o codigo da configuração do banco de dados
        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()

        listaTarefas.forEach { tarefa ->
            Log.i("info_db", "${tarefa.descricao}")
        }

    }

}