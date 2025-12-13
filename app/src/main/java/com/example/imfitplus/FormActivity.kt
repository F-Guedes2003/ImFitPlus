package com.example.imfitplus

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imfitplus.controller.FormController
import com.example.imfitplus.databinding.ActivityFormBinding
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo
import java.time.LocalDate
import java.time.Period
import kotlin.math.pow

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
        val altura = afb.altura
        val diaNascimento = afb.dia
        val mesNascimento = afb.mes
        val anoNascimento = afb.ano
        val peso = afb.peso

        limparErros(nome, altura, peso)

        if (!validarCampoObrigatorio(nome, "Nome")) return
        if (!validarCampoObrigatorio(diaNascimento, "Dia de Nascimento")) return
        if (!validarCampoObrigatorio(mesNascimento, "Mes de Nascimento")) return
        if (!validarCampoObrigatorio(anoNascimento, "Ano de Nascimento")) return
        if (!validarCampoObrigatorio(altura, "Altura")) return
        if (!validarCampoObrigatorio(peso, "Peso")) return

        val diaNascimentoInt = diaNascimento.text.toString().toIntOrNull()
        val mesNascimentoInt = mesNascimento.text.toString().toIntOrNull()
        val anoNascimentoInt = anoNascimento.text.toString().toIntOrNull()
        val alturaDouble = altura.text.toString().toDoubleOrNull()
        val pesoDouble = peso.text.toString().toDoubleOrNull()

        if (diaNascimentoInt == null || diaNascimentoInt <= 0 || diaNascimentoInt > 31) {
            exibirErro(diaNascimento, "Dia de nascimento inválido")
            return
        }

        if (mesNascimentoInt == null || mesNascimentoInt <= 0 || mesNascimentoInt > 12) {
            exibirErro(mesNascimento, "Mês de nascimento inválido")
            return
        }

        if (anoNascimentoInt == null || anoNascimentoInt <= 0) {
            exibirErro(anoNascimento, "Ano de nascimento inválido")
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

        val dataNascimento = LocalDate.of(anoNascimentoInt, mesNascimentoInt, diaNascimentoInt)
        val idade = calculaIdade(dataNascimento)
        val imc = calculaImc(pesoDouble, alturaDouble)
        val taxaMetabolica = calculaTmb(sexo, alturaDouble, pesoDouble, idade)
        val gastoDiario = taxaMetabolica * fatorAtividade(nivelAtividade)
        val pesoIdeal = calculaPesoIdeal(alturaDouble)
        val frequenciaMaxima = 220 - idade

        val pessoa = Pessoa(
            nome.text.toString(),
            dataNascimento.toString(),
            frequenciaMaxima,
            idade,
            sexo,
            nivelAtividade,
            alturaDouble,
            pesoDouble,
            imc,
            taxaMetabolica,
            gastoDiario,
            pesoIdeal,
            null
        )

        val id = formController.create(pessoa)

        println("id: " + id)
        val intent = Intent(this, ImcActivity::class.java).apply {
            putExtra("Pessoa", pessoa)
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

    private fun calculaImc(peso: Double, altura: Double): Double {
        return peso / (altura.pow(2))
    }

    private fun calculaTmb(sexo: Sexo, altura: Double, peso: Double, idade: Int): Double {
        return when (sexo) {
            Sexo.MASCULINO -> 66 + (13.7 * peso) + (5 * altura * 100) - (6.8 * idade)
            Sexo.FEMININO -> 655 + (9.6 * peso) + (1.8 * altura * 100) - (4.7 * idade)
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

    private fun calculaPesoIdeal(altura: Double): Double {
        return 22 * altura.pow(2)
    }

    private fun calculaIdade(birthDate: LocalDate): Int {
        val currentDate = LocalDate.now()
        return Period.between(birthDate, currentDate).years
    }
}
