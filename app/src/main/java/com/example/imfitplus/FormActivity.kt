package com.example.imfitplus

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imfitplus.controller.FormController
import com.example.imfitplus.controller.MainController
import com.example.imfitplus.databinding.ActivityFormBinding
import com.example.imfitplus.entities.DadosSaude
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo

class FormActivity : AppCompatActivity() {
    private lateinit var afb: ActivityFormBinding

    private val formController: FormController by lazy {
        FormController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        afb = ActivityFormBinding.inflate(layoutInflater)
        setContentView(afb.root)

        afb.botaoEnviarFormulario.setOnClickListener { enviar() }
    }

    private fun enviar() {
        val nome = afb.nome
        val idade = afb.idade
        val altura = afb.altura
        val peso = afb.peso

        limparErros(nome, idade, altura, peso)

        if (!validarCampoObrigatorio(nome, "Nome")) return
        if (!validarCampoObrigatorio(idade, "Idade")) return
        if (!validarCampoObrigatorio(altura, "Altura")) return
        if (!validarCampoObrigatorio(peso, "Peso")) return

        val idadeInt = idade.text.toString().toIntOrNull()
        val alturaDouble = altura.text.toString().toDoubleOrNull()
        val pesoDouble = peso.text.toString().toDoubleOrNull()

        if (idadeInt == null || idadeInt <= 0) {
            exibirErro(idade, "Idade inválida")
            return
        }

        if (alturaDouble == null || alturaDouble <= 0) {
            exibirErro(altura, "Altura inválida")
            return
        }

        if (pesoDouble == null || pesoDouble <= 0) {
            exibirErro(peso, "Peso inválido")
            return
        }

        val sexo = selectSexo()
        if (sexo == null) {
            Toast.makeText(this, "Selecione o sexo", Toast.LENGTH_SHORT).show()
            return
        }

        val nivelAtividade = selectNivelAtividade()
        if (nivelAtividade == null) {
            Toast.makeText(this, "Selecione o nível de atividade", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, ImcActivity::class.java).apply {
            putExtra(
                "Pessoa",
                Pessoa(
                    nome.text.toString(),
                    idadeInt,
                    sexo,
                    nivelAtividade,
                    alturaDouble,
                    pesoDouble
                )
            )
        }
        startActivity(intent)
    }

    private fun validarCampoObrigatorio(campo: EditText, nomeCampo: String): Boolean {
        if (campo.text.toString().trim().isEmpty()) {
            exibirErro(campo, "Campo $nomeCampo deve ser preenchido")
            return false
        }
        return true
    }

    private fun exibirErro(campo: EditText, mensagem: String) {
        campo.background.setTint(Color.RED)
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    private fun limparErros(vararg campos: EditText) {
        campos.forEach { campo -> campo.background.setTint(Color.GRAY) }
    }

    private fun selectSexo(): Sexo? {
        val selectedRadioButtonId = afb.rgSexo.checkedRadioButtonId
        if (selectedRadioButtonId == -1) return null

        return when (findViewById<RadioButton>(selectedRadioButtonId).text.toString()) {
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
