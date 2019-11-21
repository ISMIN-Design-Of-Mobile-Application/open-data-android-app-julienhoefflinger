package com.ismin.opendataapp.info

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ismin.opendataapp.R
import com.ismin.opendataapp.Restaurant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import java.io.Serializable


class InfoActivity : AppCompatActivity(), Serializable{

    private lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ismin.opendataapp.R.layout.activity_info)

        restaurant = intent.getSerializableExtra("EXTRA_RESTAURANT") as Restaurant

        Log.d("onresponse response", restaurant.fields.photo);

        val imageViewPhoto = findViewById<ImageView>(R.id.activity_info_imageView)
        val textViewZone = findViewById<TextView>(R.id.activity_info_zone)
        val textViewType = findViewById<TextView>(R.id.activity_info_type)
        val textViewTitre = findViewById<TextView>(R.id.activity_info_titre)
        val textViewInfo = findViewById<TextView>(R.id.activity_info_info)


//        Picasso.get().load(restaurant.fields.photo).into(imageViewPhoto);
        imageViewPhoto.setImageResource(R.mipmap.crous)
        textViewZone.text = "Ville : "+restaurant.fields.zone
        textViewType.text = "Type : "+restaurant.fields.type
        textViewTitre.text = "Titre : "+restaurant.fields.title
        textViewInfo.text = "Infos : "+restaurant.fields.infos
    }

}