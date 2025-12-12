package com.example.imfitplus.entities

interface PessoaDAO {
    fun create(dado: Pessoa): Long

    fun getAllDados(): MutableList<Pessoa>

    fun deleteDado(id: Long): Int

    fun updatePessoa(id: Long, pessoa: Pessoa): Int
}