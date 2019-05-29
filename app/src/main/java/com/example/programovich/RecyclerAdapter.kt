package com.example.programovich


import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.programovich.Help.Image
import com.example.programovich.Help.Jsons
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val images:ArrayList<Image>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =images.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val json = Jsons()
       holder.bindImage(images[pos])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,SecondActivity::class.java)
            intent.putExtra("birdD",json.itemToJson(images[pos]))
            holder.itemView.context.startActivity(intent)
        }
    }
    fun removeAt(position: Int) {

        images.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val pic: ImageView
        val name: TextView
        val tags:TextView
        val dates:TextView

        init {
            pic=itemView.findViewById(R.id.item_image)
            name=itemView.findViewById(R.id.item_name)
            tags=itemView.findViewById(R.id.item_tags)
            dates=itemView.findViewById(R.id.item_date)
            itemView.setOnClickListener { v: View  ->

                val intent= Intent(v.context,Add_image::class.java)
            }
        }


        fun bindImage(image: Image){
            name.text=image.name
            Picasso.get().load(image.pic).into(pic)
            dates.text=image.date
            tags.text=image.tags

        }


    }


}