package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityFormBinding
import com.example.imfitplus.databinding.ActivityPesoIdealBinding
import com.example.imfitplus.databinding.ActivityResumoSaudeBinding
import com.example.imfitplus.entities.Pessoa

class ResumoSaudeActivity : AppCompatActivity() {
    lateinit var arsb: ActivityResumoSaudeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arsb = ActivityResumoSaudeBinding.inflate(layoutInflater)
        setContentView(arsb.root)

        inicializaBotaoMenu(arsb)

        val pessoa = intent.getParcelableExtra<Pessoa>("Pessoa")
        arsb.valorImc.text = "%.2f".format(pessoa!!.imc)
        arsb.valorPesoIdeal.text = "%.2f".format(pessoa.pesoIdeal)
        arsb.tmbValor.text = "%.2f".format(pessoa.taxaMetabolica)
    }

    private fun inicializaBotaoMenu(activity: ActivityResumoSaudeBinding) {
        activity.menuBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}