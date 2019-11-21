package com.ismin.opendataapp

import android.media.Image
import java.io.Serializable

data class RestaurantCrousResponse(
    val records: List<Restaurant> = ArrayList()
) : Serializable

    data class Restaurant(
        val fields: RestaurantFields
    ) : Serializable

        data class RestaurantFields(
            val title: String,
            val zone: String,
            val type: String,
            val image: Image,
            val geolocalisation: List<Double> = ArrayList(),
            val infos: String,
            val photo: String
        ) : Serializable

//            data class Coordinate(
//                val coordnate: Double
//            ) : Serializable