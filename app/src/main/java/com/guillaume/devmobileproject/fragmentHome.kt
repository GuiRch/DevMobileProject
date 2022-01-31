package com.guillaume.devmobileproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

// new since Glide v4
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // leave empty for now
}

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val photos : MutableList<Photo> = ArrayList()

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentHome : Fragment() {

    // navigate to fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()

        val player = view.findViewById<AppCompatImageView>(R.id.imageButton4)
        player.setOnClickListener {
            navController.navigate(R.id.action_fragmentHome_to_playerFragment)
        }
        //profil picture
        val url = "https://source.unsplash.com/random"
        val user = view.findViewById<ImageButton>(R.id.userIcon2)
        Glide.with(this).load(url).circleCrop().error(R.drawable.user_icon).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(user)
        // music list
        val recyclerView1 = view.findViewById<View>(R.id.recyclerview1) as RecyclerView
        recyclerView1.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL ,false )
        recyclerView1.adapter = CustomAdapter(myTestlist)
        val recyclerView2 = view.findViewById<View>(R.id.recyclerview2) as RecyclerView
        recyclerView2.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL ,false )
        recyclerView2.adapter = CustomAdapter(myTestlist)
        //nav
        user.setOnClickListener {
            navController.navigate(R.id.action_fragmentHome_to_profileFragment)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    class CustomAdapter(val dataSet: Array<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView
            val imageView: ImageView

            init {
                // Define click listener for the ViewHolder's View.
                textView = view.findViewById(R.id.textView)
                imageView = view.findViewById(R.id.imageItem)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.musicitem_layout, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView.text = dataSet[position]
            //val dataSetList =dataSet[position]
            val url = "https://source.unsplash.com/random"
            Glide.with(viewHolder.imageView).load(url).centerCrop().error(R.drawable.audio_player).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.imageView)

            /*
            // use of retrofit
            val page = 1
            val sort = "popular"
            //var photos : MutableList<Photo> = ArrayList()
            val getPost = RetrofitInstance.api.getPhotos(page,5,sort)
            getPost.enqueue(object : Callback<MutableList<Photo>> {
                override fun onResponse(
                    call: Call<MutableList<Photo>>,
                    response: Response<MutableList<Photo>>
                ) {
                    if(response.isSuccessful)
                    {
                        photos.clear()
                        Log.d("response",response.body().toString())
                        response.body()?.let { photos.addAll(it)}
                        viewHolder.textView.text =photos[position].id
                        Glide.with(viewHolder.imageView)
                            .load(photos[position].url.regular)
                            //.placeholder(ColorDrawable(Color.parseColor(photos[position].color)))
                            .centerCrop()
                            .error(R.drawable.audio_player)
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(viewHolder.imageView)
                    }
                    else
                        Log.d("response",response.body().toString())
                }

                override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                    Log.d("Response","Failed")
                }

            })*/
        }

        // Return the size of your dataset (invoked by the layout manager)
        //override fun getItemCount() = dataSet.size
        override fun getItemCount(): Int {
            //return photos.size
            return dataSet.size
        }

    }

    var myTestlist = arrayOf("Artiste1", "Artiste2","Artiste3", "Artiste4","Artiste5", "Artiste6","Artiste7", "Artiste8","Artiste9", "Artiste10")
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}