package com.example.imfitplus.entities

interface DadosSaudeDAO {
    fun create(dado: Pessoa): Long

    fun getAllDados(): List<Pessoa>
}