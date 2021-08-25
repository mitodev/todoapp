package com.example.todoapp.Fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.models.Todo
import com.example.todoapp.data.models.prio
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var datalist= emptyList<Todo>()

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){}

    // ? getting our row layout ui from the xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    // ? data binding and setting the data passing
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.title_txt.text=datalist[position].title
        holder.itemView.discrition_txt.text=datalist[position].description
        when(datalist[position].Prio) {
            prio.High-> holder.itemView.prio_ind.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context, R.color.red))
            prio.Mid-> holder.itemView.prio_ind.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context, R.color.yellow))
            prio.Low-> holder.itemView.prio_ind.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context, R.color.green))
        }

        holder.itemView.row_background.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(datalist[position])
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setData(todo: List<Todo>){
        this.datalist = todo
        notifyDataSetChanged()
    }
}