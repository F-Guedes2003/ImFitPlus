package com.example.imfitplus.entities

import android.os.Parcelable
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pessoa(
    val nome: String,
    val idade: Int,
    val sexo: Sexo,
    val nivelAtividade: NivelAtividade,
    val altura: Double,
    val peso: Double
    ): Parcelable
