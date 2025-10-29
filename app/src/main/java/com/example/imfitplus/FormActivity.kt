package com.example.imfitplus

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityFormBinding
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo

class FormActivity : AppCompatActivity() {
    lateinit var afb: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        afb = ActivityFormBinding.inflate(layoutInflater)
        setContentView(afb.root)

        afb.botaoEnviarFormulario.setOnClickListener { enviar() }
    }

    private fun enviar() {
        val nome = afb.nome
        val idade = afb.idade
        val nivelAtividade = selectNivelAtividade()
        val altura = afb.altura
        val peso = afb.peso

        if (nome.text == null) {
            Toast.makeText(afb.root.context, "Campo nome deve ser preenchido", Toast.LENGTH_LONG)
                .show()

            nome.background.setTint(Color.RED)

            return
        }

        if (idade.text == null) {
            Toast.makeText(afb.root.context, "Campo idade deve ser preenchido", Toast.LENGTH_LONG)
                .show()

            idade.background.setTint(Color.RED)

            return
        }

        if (selectSexo() == null) {
            Toast.makeText(afb.root.context, "Campo sexo deve ser preenchido", Toast.LENGTH_LONG)
                .show()

            return
        }

        if (selectNivelAtividade() == null) {
            Toast.makeText(afb.root.context, "Campo nivel de atividade deve ser preenchido", Toast.LENGTH_LONG)
                .show()

            return
        }

        if (altura.text == null) {
            Toast.makeText(afb.root.context, "Campo altura deve ser preenchido", Toast.LENGTH_LONG)
                .show()

            altura.background.setTint(Color.RED)

            return
        }

        if (peso.text == null) {
            Toast.makeText(afb.root.context, "Campo peso deve ser preenchido", Toast.LENGTH_LONG)
                .show()

            peso.background.setTint(Color.RED)

            return
        }

        val intent = Intent(this, ImcActivity::class.java)
        intent.putExtra("Pessoa", Pessoa(
            nome.text.toString(),
            idade.text.toString().toInt(),
            selectSexo()!!,
            nivelAtividade!!,
            altura.text.toString().toDouble(),
            peso.text.toString().toDouble()
        ))
        startActivity(intent);
    }

    private fun selectSexo(): Sexo? {
        val selectedRadioButtonId = afb.rgSexo.checkedRadioButtonId

        if (selectedRadioButtonId == -1) return null

        return when (afb.root.findViewById<RadioButton>(selectedRadioButtonId).text) {
                "Masculino" -> Sexo.MASCULINO
                "Feminino" -> Sexo.FEMININO
                else -> null
        }
    }

    private fun selectNivelAtividade(): NivelAtividade? {
        return when (afb.nivelAtividade.selectedItem.toString()) {
            "Sedentário" -> NivelAtividade.SEDENTARIO
            "Leve" -> NivelAtividade.LEVE
            "Moderado" -> NivelAtividade.MODERADO
            "Intenso" -> NivelAtividade.INTENSO
            else -> null
        }
    }
}