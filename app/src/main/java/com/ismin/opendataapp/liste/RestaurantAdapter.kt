package com.ismin.opendataapp.liste

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.ismin.opendataapp.R
import com.ismin.opendataapp.Restaurant

class RestaurantAdapter(private val restaurants: ArrayList<Restaurant>, private val onItemClickListener: AdapterView.OnItemClickListener) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_crous_restaurant, parent,
            false)

        return RestaurantViewHolder(row, onItemClickListener)
    }

    override fun onBindViewHolder(viewholder: RestaurantViewHolder, position: Int) {
        val (name, zone, type) = this.restaurants[position].fields

        viewholder.restaurantName.text = Html.fromHtml("<u>"+name+"</u>")
        viewholder.restaurantZone.text = zone
        when (type) {
            "Restaurant" -> viewholder.restaurantImage.setImageResource(R.drawable.ic_restaurant)
            "Cafétéria" -> viewholder.restaurantImage.setImageResource(R.drawable.ic_cafeteria)
            else -> viewholder.restaurantImage.setImageResource(R.drawable.ic_other)
        }
    }

    override fun getItemCount(): Int {
        return this.restaurants.size
    }
}
