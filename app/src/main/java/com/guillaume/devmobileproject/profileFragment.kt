package com.guillaume.devmobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.findNavController
import java.time.temporal.TemporalAdjusters.next
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class profileFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        val Home= view.findViewById<AppCompatImageView>(R.id.homeBtn)
        Home.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_fragmentHome)
        }
        val favBtn = view.findViewById<Button>(R.id.favBtn)
        favBtn.setOnClickListener {
            navController.navigate(R.id.action_profileFragment_to_favorite)
        }
        val url2 = "https://source.unsplash.com/random"
        val user = view.findViewById<ImageView>(R.id.userIcon)
        Glide.with(this).load(url2).circleCrop().error(R.drawable.user_icon).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(user)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}