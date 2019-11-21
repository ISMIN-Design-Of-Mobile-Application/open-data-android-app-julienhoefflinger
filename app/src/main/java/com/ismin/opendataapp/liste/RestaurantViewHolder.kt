package com.ismin.opendataapp.liste

import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ismin.opendataapp.R

class RestaurantViewHolder (rootView: View, onItemClickListener: AdapterView.OnItemClickListener) : RecyclerView.ViewHolder(rootView) {
    var restaurantName: TextView
    var restaurantZone: TextView
    var restaurantImage: ImageView
    var openDetail: TextView

    init {
        this.restaurantName = rootView.findViewById(R.id.f_row_crous_restaurant_titre)
        this.restaurantZone = rootView.findViewById(R.id.f_row_crous_restaurant_zone)
        this.restaurantImage = rootView.findViewById(R.id.f_row_crous_restaurant_type)
        this.openDetail = rootView.findViewById(R.id.f_row_crous_restaurant_titre)
        this.openDetail.setOnClickListener {
            onItemClickListener.onItemClick(null, it, adapterPosition, 0L)
        }
    }
}
