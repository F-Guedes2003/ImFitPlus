package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.imfitplus.adapter.PessoaAdapter
import com.example.imfitplus.controller.MainController
import com.example.imfitplus.databinding.ActivityGastoCaloricoBinding
import com.example.imfitplus.databinding.ActivityHistoricoBinding
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo

class HistoricoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoBinding

    private val pessoaController: MainController by lazy {
        MainController(this)
    }

    private lateinit var lista: MutableList<Pessoa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializaBotaoMenu(binding)

        lista = pessoaController.getAllDados()

        val adapter = PessoaAdapter(this, lista)
        binding.myListView.adapter = adapter

        binding.myListView.setOnItemClickListener { parent, view, position, id ->
            val pessoa = lista[position]
            var intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("Pessoa", pessoa)
            startActivity(intent)
        }
    }

    private fun inicializaBotaoMenu(activity: ActivityHistoricoBinding) {
        activity.menuBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
