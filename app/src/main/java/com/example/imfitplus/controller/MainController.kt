package com.example.imfitplus.controller

import com.example.imfitplus.MainActivity
import com.example.imfitplus.MenuActivity
import com.example.imfitplus.entities.DadosSaude
import com.example.imfitplus.entities.DadosSaudeDAO
import com.example.imfitplus.entities.DadosSaudeSQLite

class MainController(menuActivity: MenuActivity) {
    private var dadoSaudeDao: DadosSaudeDAO = DadosSaudeSQLite(menuActivity)

    fun insertDado(dado: DadosSaude) = dadoSaudeDao.create(dado)

}