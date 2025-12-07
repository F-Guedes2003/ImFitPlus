package com.example.imfitplus.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DadosSaude(
    var imc: Double,
    var taxaMetabolica: Double,
    var gastoDiario: Double,
    var pesoIdeal: Double
): Parcelable
