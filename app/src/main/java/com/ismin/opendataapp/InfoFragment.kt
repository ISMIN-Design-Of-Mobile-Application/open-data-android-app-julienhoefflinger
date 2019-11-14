package com.ismin.opendataapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class InfoFragment : Fragment() {
    private var listener: OnInfoFragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    fun onInfoButtonPressed() {
        listener?.onInfoFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInfoFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnInfoFragmentListener {
        fun onInfoFragment()
    }

}
