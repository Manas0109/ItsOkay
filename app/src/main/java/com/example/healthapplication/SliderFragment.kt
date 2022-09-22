package com.example.healthapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class SliderFragment : Fragment() {

    var pageTitle : String = ""
    var pageDesc : String = ""
    var pageimage : String = ""

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var fragment_Title = view.findViewById<TextView>(R.id.fragment_title)
        fragment_Title?.text = pageTitle

        var fragment_Desc = view.findViewById<TextView>(R.id.desc_intro)
        fragment_Desc?.text = pageDesc


        var fragment_Image = view.findViewById<ImageView>(R.id.fragment_image)
        Glide.with(this)
            .load(pageimage)
            .into(fragment_Image)
        Log.v("manas","$pageimage")
    }

    fun setTitle(title : String, desc : String, imgurl : String){

        pageTitle = title
        pageimage = imgurl
        pageDesc = desc
    }
}