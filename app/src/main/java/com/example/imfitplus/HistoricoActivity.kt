package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imfitplus.adapter.PessoaAdapter
import com.example.imfitplus.databinding.ActivityHistoricoBinding
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo

class HistoricoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoBinding
    private lateinit var lista: MutableList<Pessoa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lista = carregarHistoricoFake()

        val adapter = PessoaAdapter(this, lista)
        binding.myListView.adapter = adapter
    }

    private fun carregarHistoricoFake(): MutableList<Pessoa> {
        return mutableListOf(
            Pessoa("Ronaldo", 40, Sexo.MASCULINO,
                NivelAtividade.INTENSO,
                1.85, 120.0, 30.0, 20.4, 2132.3, 22.0),

            Pessoa("Zara Vitória", 30, Sexo.FEMININO,
                NivelAtividade.MODERADO,
                1.60, 65.0, 0.0, 0.0, 0.0, 0.0)
        )
    }
}
