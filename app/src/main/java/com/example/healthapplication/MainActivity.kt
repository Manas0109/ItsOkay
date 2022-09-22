package com.example.healthapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    val fragment1 = SliderFragment()
    val fragment2 = SliderFragment()
    val fragment3 = SliderFragment()
    lateinit var adapter : myPagerAdapter
    lateinit var activity : Activity

    lateinit var preferences: SharedPreferences
    val pref_show_intro = "Intro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity=this

        preferences = getSharedPreferences("layout", Context.MODE_PRIVATE)

//        if(!preferences.getBoolean(pref_show_intro,true)){
//            startActivity(Intent(activity,SignInActivity::class.java))
//            finish()
//        }
        var btn_next = findViewById<Button>(R.id.btn_next)

        fragment1.setTitle("Open Up Anonymously", "Post as anonymous let those feelings flow out!!","https://firebasestorage.googleapis.com/v0/b/foodapplication-34a34.appspot.com/o/9800.jpg?alt=media&token=bf62b829-7f02-43b9-bebd-6f9723357303")
        fragment2.setTitle("Quality Viewing ","Find solace in some amazing video content","https://firebasestorage.googleapis.com/v0/b/healthapp-e12d3.appspot.com/o/frag1.png?alt=media&token=75b6303e-a845-4649-af6a-680e5964f032")
        fragment3.setTitle("Know Yourself","Write about how you feel regularly","https://firebasestorage.googleapis.com/v0/b/healthapp-e12d3.appspot.com/o/animatedimg1.jpg?alt=media&token=39a6decc-9e85-495b-a8dc-75c5b3e5cb55")


        adapter = myPagerAdapter(supportFragmentManager)
        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)

        var view_pager = findViewById<ViewPager>(R.id.view_pager)
        view_pager.adapter = adapter

        btn_next.setOnClickListener {
            view_pager.currentItem++
        }

        var indicator1 = findViewById<TextView>(R.id.indicator1)
        var indicator2 = findViewById<TextView>(R.id.indicator2)
        var indicator3 = findViewById<TextView>(R.id.indicator3)


        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(position == adapter.list.size - 1){

                    btn_next.text = "DONE"
                    btn_next.setOnClickListener(){
                        startActivity(Intent(activity,SignInActivity::class.java))
                        finish()
//                        val editor = preferences.edit()
//                        editor.putBoolean(pref_show_intro,false)
//                        editor.apply()
                    }
                }
                else{
                    btn_next.text = "NEXT"
                    btn_next.setOnClickListener {
                        view_pager.currentItem++
                    }
                }
                when(view_pager.currentItem){
                    0->{
                        indicator1.setTextColor(Color.BLACK)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.GRAY)
                    }

                    1->{
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.BLACK)
                        indicator3.setTextColor(Color.GRAY)

                    }

                    2->{
                        indicator1.setTextColor(Color.GRAY)
                        indicator2.setTextColor(Color.GRAY)
                        indicator3.setTextColor(Color.BLACK)

                    }
                }
            }


            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }


    class myPagerAdapter(manager : FragmentManager) : FragmentPagerAdapter(manager){

        val list : MutableList<Fragment> = ArrayList()

        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list[position]
        }

    }
}