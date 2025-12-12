package com.example.imfitplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imfitplus.databinding.ActivityDetalhesBinding
import com.example.imfitplus.entities.Pessoa

class DetalhesActivity : AppCompatActivity() {
    lateinit var adb: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adb = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(adb.root)

        val pessoa = intent.getParcelableExtra<Pessoa>("Pessoa")
        adb.nome.text = pessoa!!.nome
        adb.id.text = pessoa.id.toString()
    }
}