package com.example.imfitplus.controller

import com.example.imfitplus.HistoricoActivity
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.entities.PessoaDAO
import com.example.imfitplus.entities.PessoaSQLite

class MainController(historicoActivity: HistoricoActivity) {
    private var pessoaDao: PessoaDAO = PessoaSQLite(historicoActivity)

    fun getAllDados(): MutableList<Pessoa> = pessoaDao.getAllDados()

}