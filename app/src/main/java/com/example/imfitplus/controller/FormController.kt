package com.example.imfitplus.controller

import com.example.imfitplus.FormActivity
import com.example.imfitplus.entities.DadosSaude
import com.example.imfitplus.entities.DadosSaudeDAO
import com.example.imfitplus.entities.DadosSaudeSQLite

class FormController(formActivity: FormActivity) {
    private val dadosSaudeDAO: DadosSaudeDAO = DadosSaudeSQLite(formActivity)

    fun create(dado: DadosSaude) = dadosSaudeDAO.create(dado)
}