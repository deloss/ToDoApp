package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.tarea_item.view.*

class TareaAdapter(private var tareas: ArrayList<Tarea> = ArrayList()) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    class TareaViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
        notifyItemInserted(itemCount)
    }

    fun setTareas(tareas : ArrayList<Tarea>) {
        this.tareas = tareas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tarea_item, parent, false)
        return TareaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tareas.size
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        holder.itemView.nombre.text = tareas[position].nombre
        holder.itemView.tarea_terminada.isChecked = tareas[position].terminada
        if(holder.itemView.tarea_terminada.isChecked)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.tarea_terminada))
        else
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.tarea_no_terminada))
        holder.itemView.tarea_terminada.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.tarea_terminada))
            else
                holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.tarea_no_terminada))
        }
    }

}