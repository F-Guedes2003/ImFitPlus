package com.example.imfitplus.controller

import com.example.imfitplus.FormActivity
import com.example.imfitplus.entities.DadosSaude
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.entities.PessoaDAO
import com.example.imfitplus.entities.PessoaSQLite

class FormController(formActivity: FormActivity) {
    private val pessoaDAO: PessoaDAO = PessoaSQLite(formActivity)

    fun create(pessoa: Pessoa) = pessoaDAO.create(pessoa)
}