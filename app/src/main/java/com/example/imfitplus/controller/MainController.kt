package com.example.imfitplus.controller

import com.example.imfitplus.MenuActivity
import com.example.imfitplus.entities.DadosSaude
import com.example.imfitplus.entities.Pessoa
import com.example.imfitplus.entities.PessoaDAO
import com.example.imfitplus.entities.PessoaSQLite

class MainController(menuActivity: MenuActivity) {
    private var dadoSaudeDao: PessoaDAO = PessoaSQLite(menuActivity)

    fun insertDado(dado: Pessoa) = dadoSaudeDao.create(dado)

}