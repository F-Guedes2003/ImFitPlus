package com.example.imfitplus

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.controller.MainController
import com.example.imfitplus.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var amb: ActivityMenuBinding

    private val mainController: MainController by lazy {
        MainController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMenuBinding.inflate(layoutInflater)

        setContentView(amb.root)

        val btnCalculo = amb.btnCalcular
        btnCalculo.setOnClickListener {
            val Intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }
    }
}