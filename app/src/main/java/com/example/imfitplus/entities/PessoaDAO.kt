package com.example.imfitplus.entities

interface PessoaDAO {
    fun create(dado: Pessoa): Long

    fun getAllDados(): MutableList<Pessoa>
}