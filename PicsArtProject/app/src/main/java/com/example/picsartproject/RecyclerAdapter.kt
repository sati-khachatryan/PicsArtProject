package com.example.picsartproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(val context: FirstFragment, val elements: MutableList<PhotoItem>): RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return RecyclerViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = elements.get(position)

        holder.idView?.text = item.id.toString()
        holder.titleView?.text = item.title

        Picasso.get()
            .load(item.url)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView)

    }


}