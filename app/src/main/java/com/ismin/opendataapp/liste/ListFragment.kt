package com.ismin.opendataapp.liste

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismin.opendataapp.R
import com.ismin.opendataapp.Restaurant
import com.ismin.opendataapp.info.InfoActivity

const val BOTTLES_ARGUMENTS_KEY = "BOTTLES_ARGUMENTS_KEY"

class ListFragment : Fragment() {
    private var listener: OnListFragmentListener? = null
    private var restaurants  = ArrayList<Restaurant>()
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        restaurants = arguments!!.getSerializable(BOTTLES_ARGUMENTS_KEY) as ArrayList<Restaurant>;

        val rootview = inflater.inflate(R.layout.fragment_list, container, false)

        val listeRestaurants = rootview.findViewById<RecyclerView>(R.id.f_list_liste_restaurants)
        restaurantAdapter = RestaurantAdapter(restaurants,
            AdapterView.OnItemClickListener {
                parent, view, position, id ->
                val intent = Intent(context, InfoActivity::class.java)
                intent.putExtra("EXTRA_RESTAURANT", restaurants.get(position))
                this.startActivity(intent)
            })
        listeRestaurants.adapter = restaurantAdapter
        listeRestaurants.layoutManager = LinearLayoutManager(context)

        // Inflate the layout for this fragment
        return rootview;
    }

    fun onListButtonPressed() {
        listener?.onListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentListener {
        fun onListFragment()
    }

}
