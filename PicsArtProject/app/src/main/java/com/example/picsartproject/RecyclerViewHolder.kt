package com.example.picsartproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var idView: TextView?
    var titleView: TextView?
    var imageView: ImageView?

    init {

        idView = itemView.findViewById(R.id.id)
        titleView = itemView.findViewById(R.id.title)
        imageView = itemView.findViewById(R.id.image)

    }


}