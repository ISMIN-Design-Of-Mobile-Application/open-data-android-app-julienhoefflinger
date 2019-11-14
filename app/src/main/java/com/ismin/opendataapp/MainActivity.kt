package com.ismin.opendataapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN

class MainActivity : AppCompatActivity()
        , ListFragment.OnListFragmentListener
        , CarteFragment.OnCarteFragmentListener
        , InfoFragment.OnInfoFragmentListener {

    private lateinit var btnGoToList: AppCompatButton
    private lateinit var btnGoToMap: AppCompatButton
    private lateinit var btnGoToInfo: AppCompatButton

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
    }

    /**
     * Liste Fragment
     */
    private fun putListFragment() {
        val fragment = ListFragment()

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

}
