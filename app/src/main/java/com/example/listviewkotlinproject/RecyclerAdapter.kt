package com.example.listviewkotlinproject

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RecyclerAdapter(var context :Context, var list: List<Massage>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemview= LayoutInflater.from(context).inflate(R.layout.recyclerviewitems, parent, false)
        return ViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTextID?.text = list[position].id.toString()
        holder.itemText?.text = list[position].text
        if(list[position].image!="")
            Picasso.with(context).load(list[position].image).placeholder(R.mipmap.ic_launcher).into(holder.itemImageView)
        else
            Picasso.with(context).load(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(holder.itemImageView)
    }

    class ViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        var itemImageView: ImageView?= null
        var itemTextID: TextView? = null
        var itemText: TextView? = null
        init{
            itemText = itemView?.findViewById(R.id.itemText)
            itemTextID =itemView?.findViewById(R.id.itemTextID)
            itemImageView = itemView?.findViewById(R.id.itemImageView)
        }
    }


}