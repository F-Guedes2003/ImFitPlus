package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityImcBinding
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

        inicializaBotaoMenu(pia)

        val pessoa = intent.getParcelableExtra<Pessoa>("Pessoa")
        val pesoIdeal = pessoa!!.pesoIdeal

        pia.pesoAtualValor.text = pessoa.peso.toString()
        pia.pesoIdealValor.text = "%.2f".format(pesoIdeal)
        pia.diferencaPesos.text = "%.2f".format(pesoIdeal - pessoa.peso)

        pia.btnVoltar.setOnClickListener { finish() }
        pia.btnAvancarParaResumo.setOnClickListener {
            val intent = Intent(this, ResumoSaudeActivity::class.java)
            intent.putExtra("Pessoa", pessoa)
            startActivity(intent)
        }
    }

    private fun inicializaBotaoMenu(activity: ActivityPesoIdealBinding) {
        activity.menuBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}