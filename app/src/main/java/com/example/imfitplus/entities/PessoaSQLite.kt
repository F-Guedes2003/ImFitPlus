package com.example.imfitplus.entities

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.util.Log
import com.example.imfitplus.enums.NivelAtividade
import com.example.imfitplus.enums.Sexo
import java.sql.SQLException
import kotlin.apply

class PessoaSQLite(context: Context): PessoaDAO {
    companion object {
        private val DADOS_SAUDE_FILE = "dadosSaudeList"
        private val DADOS_SAUDE_TABLE = "dados_saude"

        private val ID_COLUMN = "id"

        private val DATA_NASC_COLUMN = "data_nasc"

        private val FREQUENCIA_MAX_COLUMN = "frequencia_max"

        private val NOME_COLUMN = "nome"

        private val IDADE_COLUMN  = "idade"

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
                "$DATA_NASC_COLUMN TEXT," +
                "$FREQUENCIA_MAX_COLUMN INTEGER," +
                "$IDADE_COLUMN INTEGER NOT NULL," +
                "$PESO_COLUMN REAL NOT NULL," +
                "$ALTURA_COLUMN REAL NOT NULL," +
                "$SEXO_COLUMN TEXT NOT NULL," +
                "$NIVEL_ATIVIDADE TEXT NOT NULL," +
                "$IMC_COLUMN REAL NOT NULL," +
                "$TMB_COLUMN REAL NOT NULL," +
                "$GASTO_DIARIO_COLUMN REAL NOT NULL," +
                "$PESO_IDEAL_COLUMN REAL NOT NULL);"
    }

    // Criando uma instância do sqlite
    private val database = context.openOrCreateDatabase(
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


    override fun getAllDados(): MutableList<Pessoa> {
        var pessoaList: MutableList<Pessoa> = mutableListOf();
        var cursor = database.rawQuery("SELECT * FROM $DADOS_SAUDE_TABLE", null)
        while(cursor.moveToNext()) {
            pessoaList.add(cursor.toPessoa())
        }

        return pessoaList
    }

    override fun deleteDado(id: Long): Int {
        return database.delete(DADOS_SAUDE_TABLE,
            "$id = ?",
            arrayOf(id.toString()))
    }

    override fun updatePessoa(id: Long, pessoa: Pessoa): Int {
        return database.update(
            DADOS_SAUDE_TABLE,
            pessoa.toContentValues(),
            "$id = ?",
            arrayOf(id.toString())
        )
    }

    private fun Pessoa.toContentValues() = ContentValues().apply {
        put(NOME_COLUMN, nome)
        put(DATA_NASC_COLUMN, dataNascimento)
        put(FREQUENCIA_MAX_COLUMN, frequenciaMaxima)
        put(IDADE_COLUMN, idade)
        put(PESO_COLUMN, peso)
        put(ALTURA_COLUMN, altura)
        put(SEXO_COLUMN, sexo.toString())
        put(NIVEL_ATIVIDADE, nivelAtividade.toString())
        put(IMC_COLUMN, imc)
        put(TMB_COLUMN, taxaMetabolica)
        put(GASTO_DIARIO_COLUMN, gastoDiario)
        put(PESO_IDEAL_COLUMN, pesoIdeal)
    }

    private fun Cursor.toPessoa() = Pessoa(
            getString(getColumnIndexOrThrow(NOME_COLUMN)),
        getString(getColumnIndexOrThrow(DATA_NASC_COLUMN)),
        getInt(getColumnIndexOrThrow(FREQUENCIA_MAX_COLUMN)),
            getInt(getColumnIndexOrThrow(IDADE_COLUMN)),
            selectSexo(),
            selectNivelAtividade(),
            getDouble(getColumnIndexOrThrow(ALTURA_COLUMN)),
            getDouble(getColumnIndexOrThrow(PESO_COLUMN)),
            getDouble(getColumnIndexOrThrow(IMC_COLUMN)),
            getDouble(getColumnIndexOrThrow(TMB_COLUMN)),
            getDouble(getColumnIndexOrThrow(GASTO_DIARIO_COLUMN)),
            getDouble(getColumnIndexOrThrow(PESO_IDEAL_COLUMN)),
            getInt(getColumnIndexOrThrow(ID_COLUMN)),
        )


    private fun Cursor.selectSexo(): Sexo {
        val sexo = getString(getColumnIndexOrThrow(SEXO_COLUMN))

        return if (sexo == "MASCULINO") Sexo.MASCULINO else Sexo.FEMININO
    }

    private fun Cursor.selectNivelAtividade(): NivelAtividade {
        return when (getString(getColumnIndexOrThrow(NIVEL_ATIVIDADE))) {
            "SEDENTARIO" -> NivelAtividade.SEDENTARIO
            "MODERADO" -> NivelAtividade.MODERADO
            "INTENSO" -> NivelAtividade.INTENSO
            else -> NivelAtividade.SEDENTARIO
        }
    }

}