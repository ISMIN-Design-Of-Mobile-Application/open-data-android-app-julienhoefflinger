package com.ismin.opendataapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RestaurantCrousResponse(
    val records: List<Records> = ArrayList()
) : Serializable

    data class Records(
        val fields: Fields
    ) : Serializable

        data class Fields(
            val title: String,
            val type: String
        //  val geolocalisation: Geolocalisation
        ) : Serializable