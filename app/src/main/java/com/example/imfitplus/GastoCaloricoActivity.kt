package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityGastoCaloricoBinding
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo

class GastoCaloricoActivity : AppCompatActivity() {
    lateinit var agcb: ActivityGastoCaloricoBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agcb = ActivityGastoCaloricoBinding.inflate(layoutInflater)
        setContentView(agcb.root)

        val pessoa = intent.getParcelableExtra<Pessoa>("Pessoa")

        if (pessoa != null) {
            val tmb = calculaTmb(pessoa)
            val gastoDiario = tmb * fatorAtividade(pessoa.nivelAtividade)

            agcb.nomeGasto.text = pessoa.nome
            agcb.textTmbValor.text = "%.2f".format(calculaTmb(pessoa))
        }

        // Botão para próxima tela (Peso Ideal)
        agcb.btnCalcularPesoIdeal.setOnClickListener {
            val intent = Intent(this, PesoIdealActivity::class.java)
            intent.putExtra("Pessoa", pessoa)
            startActivity(intent)
        }

        // Botão para voltar
        agcb.btnVoltarTmb.setOnClickListener {
            finish()
        }
    }

    private fun calculaTmb(pessoa: Pessoa): Double {
        return when (pessoa.sexo) {
            Sexo.MASCULINO -> 66 + (13.7 * pessoa.peso) + (5 * pessoa.altura * 100) - (6.8 * pessoa.idade)
            Sexo.FEMININO -> 655 + (9.6 * pessoa.peso) + (1.8 * pessoa.altura * 100) - (4.7 * pessoa.idade)
        }
    }

    private fun fatorAtividade(nivel: NivelAtividade): Double {
        return when (nivel) {
            NivelAtividade.SEDENTARIO -> 1.2
            NivelAtividade.LEVE -> 1.375
            NivelAtividade.MODERADO -> 1.55
            NivelAtividade.INTENSO -> 1.725
        }
    }

}