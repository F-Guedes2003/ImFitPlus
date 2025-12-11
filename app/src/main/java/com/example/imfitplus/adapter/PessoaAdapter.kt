package com.example.imfitplus.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.imfitplus.R
import com.example.imfitplus.databinding.TilePessoaBinding
import com.example.imfitplus.entities.Pessoa

class PessoaAdapter(
    context: Context,
    private val pessoaList: MutableList<Pessoa>
) : ArrayAdapter<Pessoa>(
    context,
    R.layout.tile_pessoa,
    pessoaList
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val pessoa = pessoaList[position]

        var pessoaTileView = convertView
        if (pessoaTileView == null) {

            TilePessoaBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).apply {

                pessoaTileView = root

                val tilePessoaViewHolder = TilePessoaViewHolder(
                    nameTv = name,
                    idadeTv = idade,
                    sexoTv = sexo,
                    pesoTv = peso,
                    alturaTv = altura,
                    nivelAtividadeTv = nivelAtividade
                )

                pessoaTileView!!.tag = tilePessoaViewHolder
            }
        }

        val holder = pessoaTileView!!.tag as TilePessoaViewHolder

        holder.nameTv.text = pessoa.nome
        holder.idadeTv.text = "${pessoa.idade} anos"
        holder.sexoTv.text = pessoa.sexo.toString()
        holder.pesoTv.text = "${pessoa.peso} kg"
        holder.alturaTv.text = pessoa.altura.toString()
        holder.nivelAtividadeTv.text = pessoa.nivelAtividade.toString()

        return pessoaTileView!!
    }

    private data class TilePessoaViewHolder(
        val nameTv: TextView,
        val idadeTv: TextView,
        val sexoTv: TextView,
        val pesoTv: TextView,
        val alturaTv: TextView,
        val nivelAtividadeTv: TextView
    )
}
