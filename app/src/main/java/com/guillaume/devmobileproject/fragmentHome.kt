package com.guillaume.devmobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController

class fragmentHome : Fragment() {

    // navigate to fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()

        val userIcon = view.findViewById<AppCompatImageView>(R.id.userIcon)
        userIcon.setOnClickListener {
            navController.navigate(R.id.action_fragmentHome_to_profileFragment)
        }

        val playerBtn = view.findViewById<AppCompatImageView>(R.id.imageButton4)
        playerBtn.setOnClickListener {
            navController.navigate(R.id.action_fragmentHome_to_playerFragment)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}