package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityImcBinding
import com.example.imfitplus.entities.Pessoa
import kotlin.math.pow

class ImcActivity : AppCompatActivity() {
    lateinit var aib: ActivityImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aib = ActivityImcBinding.inflate(layoutInflater)
        setContentView(aib.root)

        val pessoa = intent.getParcelableExtra<Pessoa>("Pessoa")

        if (pessoa != null) {
            val imc = calculaIMC(pessoa)
            val categoria = classificaIMC(imc)

            // Exibe nome, IMC e categoria
            aib.textImc.text = """
            Nome: ${pessoa.nome}
            IMC: ${"%.2f".format(imc)}
            Categoria: $categoria
        """.trimIndent()
        }

        // Botão para próxima tela (Gasto Calórico)
        aib.btnCalcularGasto.setOnClickListener {
            val intent = Intent(this, GastoCaloricoActivity::class.java)
            intent.putExtra("Pessoa", pessoa)
            startActivity(intent)
        }

        // Botão para voltar
        aib.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun calculaIMC(pessoa: Pessoa): Double {
        return pessoa.peso / (pessoa.altura.pow(2))
    }

    private fun classificaIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}