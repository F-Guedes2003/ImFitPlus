package com.example.imfitplus.entities

interface DadosSaudeDAO {
    fun create(dado: DadosSaude): Long

    fun getAllDados(): List<DadosSaude>
}