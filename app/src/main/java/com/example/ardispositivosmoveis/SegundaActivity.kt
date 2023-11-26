package com.example.ardispositivosmoveis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SegundaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val rv: RecyclerView = findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val listaDeItens: MutableList<Item> = mutableListOf(
            Item("Texto 1"),
            Item("Texto 2"),
            Item("Texto 3"),
            Item("Texto 4"),
            Item("Texto 5"),
            Item("Texto 6"),
            Item("Texto 7"),
            Item("Texto 8"),
        )

        val adapter = Adapter()
        rv.adapter = adapter
        adapter.atualizarLista(listaDeItens)

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val ultimoItemVisivel = layoutManager.findLastCompletelyVisibleItemPosition()
                val totalItens = layoutManager.itemCount

                if (ultimoItemVisivel == totalItens - 1) {
                    listaDeItens.addAll(listaDeItens)
                    adapter.atualizarLista(listaDeItens)
                }
            }
        })
    }

    data class Item(val name: String)

    class Adapter : RecyclerView.Adapter<Adapter.MeuViewHolder>() {
        private val listaDeItens: MutableList<Item> = mutableListOf()

        fun atualizarLista(novaListaDeItens: List<Item>) {
            listaDeItens.clear()
            listaDeItens.addAll(novaListaDeItens)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeuViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MeuViewHolder(view)
        }

        override fun onBindViewHolder(holder: MeuViewHolder, position: Int) {
            val item = listaDeItens[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return listaDeItens.size
        }

        class MeuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val txtNome: TextView = itemView.findViewById(R.id.txtNome)

            fun bind(item: Item) {
                txtNome.text = item.name
            }
        }
    }
}
