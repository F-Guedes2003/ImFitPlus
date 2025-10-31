package com.example.imfitplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityFormBinding
import com.example.imfitplus.databinding.ActivityResumoSaudeBinding
import com.example.imfitplus.entities.Pessoa

class ResumoSaudeActivity : AppCompatActivity() {
    lateinit var arsb: ActivityResumoSaudeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arsb = ActivityResumoSaudeBinding.inflate(layoutInflater)
        setContentView(arsb.root)

        val imc = intent.getDoubleExtra("imc", 0.0)
        val tmb = intent.getDoubleExtra("tmb", 0.0)
        val gastoDiario = intent.getDoubleExtra("gastoDiario", 0.0)
        val pesoIdeal = intent.getDoubleExtra("pesoIdeal", 0.0)
        val pessoa = intent.getParcelableExtra<Pessoa>("pessoa")

        arsb.valorImc.text = "%.2f".format(imc)
    }
}