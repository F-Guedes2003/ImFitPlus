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
            val tmb = pessoa.taxaMetabolica

            agcb.nomeGasto.text = pessoa.nome
            agcb.textTmbValor.text = "%.2f".format(tmb)

            agcb.btnCalcularPesoIdeal.setOnClickListener {
                val intent = Intent(this, PesoIdealActivity::class.java)
                intent.putExtra("Pessoa", pessoa)
                startActivity(intent)
            }
        }

        agcb.btnVoltarTmb.setOnClickListener {
            finish()
        }
    }

}