package com.sarahbuechner.parky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//Class for user to decide to provide parking lot or to look for one
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val read= findViewById(R.id.activity_main_read) as Button
        val write= findViewById(R.id.activity_main_write) as Button
        var intent = Intent ()

        write.setOnClickListener()
        {
            intent.setClass(this, PublishParkActivity::class.java)
            startActivity(intent)
        }
        read.setOnClickListener()
        {
            intent.setClass(this, DriverPrefrActivity::class.java)
            startActivity(intent)
        }
    }
}