package com.ismin.opendataapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.GsonBuilder
import com.ismin.opendataapp.carte.CarteFragment
import com.ismin.opendataapp.info.InfoFragment
import com.ismin.opendataapp.liste.BOTTLES_ARGUMENTS_KEY
import com.ismin.opendataapp.liste.ListFragment
import kotlinx.android.synthetic.main.fragment_info.*
import okhttp3.HttpUrl
import retrofit2.http.Url


class MainActivity : AppCompatActivity()
        , ListFragment.OnListFragmentListener
        , CarteFragment.OnCarteFragmentListener
        , InfoFragment.OnInfoFragmentListener {

    private lateinit var btnGoToList: AppCompatButton
    private lateinit var btnGoToMap: AppCompatButton
    private lateinit var btnGoToInfo: AppCompatButton

    private lateinit var crousRestaurantsService: CrousRestaurantsService
    private var restaurants  = ArrayList<Restaurant>()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_refresh -> {
                resetHTTPData()
                val toast = Toast.makeText(applicationContext, "Données rafraichies avec succès", Toast.LENGTH_SHORT)
                toast.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoToList = findViewById(R.id.a_main_button_list)
        btnGoToList.setOnClickListener {
            putListFragment()
        }

        btnGoToMap = findViewById(R.id.a_main_button_carte)
        btnGoToMap.setOnClickListener {
            putCarteFragment()
        }

        btnGoToInfo = findViewById(R.id.a_main_button_info)
        btnGoToInfo.setOnClickListener {
            putInfoFragment()
        }

        resetHTTPData()
    }

    private fun resetHTTPData(){
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://data.opendatasoft.com/api/records/1.0/search/")
            .build()

        crousRestaurantsService = retrofit.create<CrousRestaurantsService>(CrousRestaurantsService::class.java)

        val url =
            HttpUrl.parse("https://data.opendatasoft.com/api/records/1.0/search/?dataset=fr_crous_restauration_france_entiere%40mesr&rows=852&facet=type&facet=zone")

        crousRestaurantsService.getRestaurants(url.toString())
            .enqueue(object : Callback<RestaurantCrousResponse> {
                override fun onResponse(
                    call: Call<RestaurantCrousResponse>,
                    response: Response<RestaurantCrousResponse>
                ) {
                    Log.d("onresponse response", response.toString());
                    var allCrousRestaurants = response.body()
                    if (allCrousRestaurants != null) {
                        restaurants = allCrousRestaurants.records as ArrayList<Restaurant>
                        putListFragment();
                    } else {
                        putListFragment();
                    }
                }

                override fun onFailure(
                    call: Call<RestaurantCrousResponse>,
                    t: Throwable
                ) {
                    putListFragment();
                }
            })
    }

    /**
     * Liste Fragment
     */
    private fun putListFragment() {
        val fragment = ListFragment()

        val bundle = Bundle()
        bundle.putSerializable(BOTTLES_ARGUMENTS_KEY, restaurants)
        fragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_main_relative_layout, fragment)
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }
    override fun onListFragment() {
    }

    /**
     * Carte Fragment
     */
    private fun putCarteFragment() {
        val fragment = CarteFragment()

        val bundle = Bundle()
        bundle.putSerializable(BOTTLES_ARGUMENTS_KEY, restaurants)
        fragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_main_relative_layout, fragment)
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }
    override fun onCarteFragment() {
    }

    /**
     * Info Fragment
     */
    private fun putInfoFragment() {
        val fragment = InfoFragment()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_main_relative_layout, fragment)
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }
    override fun onInfoFragment() {
    }

    /**
     * Requete http
     */
    interface CrousRestaurantsService {
//        @GET("/{dataset}")
//        fun getRestaurants(@Path("dataset", encoded = false) dataset : String): Call<RestaurantCrousResponse>
        @GET
        fun getRestaurants(@Url ur: String): Call<RestaurantCrousResponse>
    }

}
