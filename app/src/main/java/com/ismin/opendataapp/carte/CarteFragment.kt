package com.ismin.opendataapp.carte

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ismin.opendataapp.R
import com.ismin.opendataapp.Restaurant
import com.ismin.opendataapp.liste.BOTTLES_ARGUMENTS_KEY

class CarteFragment : Fragment(), OnMapReadyCallback {
    private var listener: OnCarteFragmentListener? = null
    private var restaurants  = ArrayList<Restaurant>()
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restaurants = arguments!!.getSerializable(BOTTLES_ARGUMENTS_KEY) as ArrayList<Restaurant>;
        return inflater.inflate(R.layout.fragment_carte, container, false)
    }

    override fun onStart() {
        super.onStart()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val centreFrance = LatLng(46.52863469527167,2.43896484375)
        mMap.addMarker(MarkerOptions().position(centreFrance).title("Centre exact de la France"))

        restaurants.forEach {
            mMap.addMarker(MarkerOptions().position(
                LatLng(it.fields.geolocalisation[0],it.fields.geolocalisation[1])
            ).title(it.fields.title))
        }

        mMap.moveCamera(CameraUpdateFactory. newLatLngZoom (centreFrance, 5.0F))
    }

    fun onCarteButtonPressed() {
        listener?.onCarteFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCarteFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnCarteFragmentListener {
        fun onCarteFragment()
    }

}
