package com.example.newbroadcast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class New_adapter(val listner : newonclick) : RecyclerView.Adapter<Newviewholder>() {

    private val arrayList : ArrayList<New_model> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Newviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_layout,parent,false)
        val viewholder = Newviewholder(view)
        view.setOnClickListener {
            listner.onclick(arrayList[viewholder.adapterPosition].url)
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: Newviewholder, position: Int) {
        holder.author.text = arrayList[position].author
        holder.title.text = arrayList[position].title
        Glide.with(holder.itemView).load(arrayList[position].imageurl).into(holder.image)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun addresult(item : ArrayList<New_model>)
    {
        arrayList.addAll(item)
        notifyDataSetChanged()
    }

}


class Newviewholder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    val author:TextView = itemView.findViewById(R.id.new_auother)
    val title :TextView = itemView.findViewById(R.id.new_title)
    val image : ImageView = itemView.findViewById(R.id.new_image)
}

interface newonclick
{
    fun onclick(url:String);
}

