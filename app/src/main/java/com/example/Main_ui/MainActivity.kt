package com.example.Main_ui

import Camera.CustomCameraUI
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_ui.*

//Main_ui : splash move
class MainActivity : AppCompatActivity() {
    //UI
    var background: ImageView? = null
    var splashTop: ImageView? = null
    var profile: ImageView? = null
    var bottomlogo: ImageView? = null
    var textsplash: LinearLayout? = null
    var texthome: LinearLayout? = null
    var menus: LinearLayout? = null
    var frombottom: Animation? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ui)

        //UI
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        background = findViewById<View>(R.id.background) as ImageView
        splashTop = findViewById<View>(R.id.splashTop) as ImageView
        profile = findViewById<View>(R.id.profile) as ImageView
        bottomlogo = findViewById<View>(R.id.bottomlogo) as ImageView
        textsplash = findViewById<View>(R.id.textsplash) as LinearLayout
        texthome = findViewById<View>(R.id.texthome) as LinearLayout
        menus = findViewById<View>(R.id.menus) as LinearLayout


        background!!.animate().translationY(-1500f).setDuration(1000).startDelay = 1000
        splashTop!!.animate().alpha(0f).setDuration(1000).startDelay = 1000
        textsplash!!.animate().translationY(140f).alpha(0f).setDuration(2200).startDelay = 2600
        
        texthome!!.startAnimation(frombottom)
        menus!!.startAnimation(frombottom)
        profile!!.startAnimation(frombottom)
        bottomlogo!!.startAnimation(frombottom)


        //camera_button onClick Event
        camera_button.setOnClickListener {
            val intent = Intent(this, CustomCameraUI::class.java)
            startActivity(intent)
        }





    }
}