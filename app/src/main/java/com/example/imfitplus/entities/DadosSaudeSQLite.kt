package com.example.imfitplus.entities

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.sql.SQLException
import kotlin.apply

class DadosSaudeSQLite(context: Context): DadosSaudeDAO {
    companion object {
        private val DADOS_SAUDE_FILE = "dadosSaudeList"
        private val DADOS_SAUDE_TABLE = "dados_saude"

        private val ID_COLUMN = "id"

        private val NOME_COLUMN = "nome"

        private val PESO_COLUMN = "peso"

        private val ALTURA_COLUMN = "altura"

        private val SEXO_COLUMN = "sexo"

        private val NIVEL_ATIVIDADE = "nivel_atividade"

        private val IMC_COLUMN = "imc"

        private val TMB_COLUMN = "tmb"

        private val GASTO_DIARIO_COLUMN = "gasto_diario"

        private val PESO_IDEAL_COLUMN = "peso_ideal"

        private val CREATE_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $DADOS_SAUDE_TABLE (" +
                "$ID_COLUMN INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$NOME_COLUMN TEXT NOT NULL," +
                "$PESO_COLUMN REAL NOT NULL," +
                "$ALTURA_COLUMN REAL NOT NULL," +
                "$SEXO_COLUMN TEXT NOT NULL," +
                "$NIVEL_ATIVIDADE TEXT NOT NULL" +
                "$IMC_COLUMN REAL NOT NULL," +
                "$TMB_COLUMN REAL NOT NULL," +
                "$GASTO_DIARIO_COLUMN REAL NOT NULL," +
                "$PESO_IDEAL_COLUMN REAL NOT NULL);"
    }

    // Criando uma instância do sqlite
    private val database = SQLiteDatabase.openOrCreateDatabase(
        DADOS_SAUDE_FILE,
        MODE_PRIVATE,
        null
    )

    init {
        try {
            database.execSQL(CREATE_TABLE_STATEMENT)
        } catch (e: SQLException) {
            Log.e("ERRO", e.message.toString());
        }
    }

    override fun create(dado: Pessoa): Long = database.insert(
        DADOS_SAUDE_TABLE,
        null,
        dado.toContentValues()
    )


    override fun getAllDados(): List<Pessoa> {
        TODO("Not yet implemented")
    }

    private fun Pessoa.toContentValues() = ContentValues().apply {
        put(NOME_COLUMN, nome)
        put(PESO_COLUMN, peso)
        put(ALTURA_COLUMN, altura)
        put(SEXO_COLUMN, sexo.toString())
        put(NIVEL_ATIVIDADE, nivelAtividade.toString())
        put(IMC_COLUMN, imc)
        put(TMB_COLUMN, taxaMetabolica)
        put(GASTO_DIARIO_COLUMN, gastoDiario)
        put(PESO_IDEAL_COLUMN, pesoIdeal)
    }
}