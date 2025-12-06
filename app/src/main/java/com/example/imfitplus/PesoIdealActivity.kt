package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityPesoIdealBinding
import com.example.imfitplus.entities.Pessoa
import kotlin.math.abs
import kotlin.math.pow

class PesoIdealActivity : AppCompatActivity() {
    private lateinit var pia: ActivityPesoIdealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pia = ActivityPesoIdealBinding.inflate(layoutInflater)
        setContentView(pia.root)
        val pessoa = intent.getParcelableExtra<Pessoa>("Pessoa")
        val imc = intent.getDoubleExtra("imc", 0.0)
        println("Valor do imc no peso ideal: " + imc)
        val tmb = intent.getDoubleExtra("tmb", 0.0)
        val gastoDiario = intent.getDoubleExtra("gastoDiario", 0.0)

        val pesoIdeal = calculaPesoIdeal(pessoa!!.altura)

        pia.pesoAtualValor.text = pessoa.peso.toString()
        pia.pesoIdealValor.text = "%.2f".format(pesoIdeal)
        pia.diferencaPesos.text = "%.2f".format(pesoIdeal - pessoa.peso)

        pia.btnVoltar.setOnClickListener { finish() }
        pia.btnAvancarParaResumo.setOnClickListener {
            val intent = Intent(this, ResumoSaudeActivity::class.java)
            intent.putExtra("pessoa", pessoa)
            intent.putExtra("imc", imc)
            intent.putExtra("tmb", tmb)
            intent.putExtra("gastoDiario", gastoDiario)
            intent.putExtra("pesoIdeal", pesoIdeal)

            startActivity(intent)
        }
    }

    private fun calculaPesoIdeal(altura: Double): Double {
        return 22 * altura.pow(2)
    }
}