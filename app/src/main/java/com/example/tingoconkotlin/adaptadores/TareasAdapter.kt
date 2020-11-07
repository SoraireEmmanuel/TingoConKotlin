package com.example.tingoconkotlin.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tingoconkotlin.R
import com.example.tingoconkotlin.clases.Tareas
import kotlinx.android.synthetic.main.tareas_recycler.view.*
import kotlin.collections.ArrayList


class TareasAdapter (private val tareas: ArrayList<Tareas> = ArrayList(), private val cellClickListener: CellClickListener): RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    class TareaViewHolder(view:View):RecyclerView.ViewHolder(view)

    //crea la vista de cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tareas_recycler, parent, false)
        return TareaViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val data=tareas[position]
        holder.itemView.tituloTarea.text = tareas[position].nombre
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener()
        }
    }

    interface CellClickListener {
        fun onCellClickListener()
    }

}





