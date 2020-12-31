package com.sarahbuechner.parky

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import io.matchmore.sdk.Matchmore
import io.matchmore.sdk.api.models.Publication

//Class for Parking lot provider to publish available lot
class PublishParkActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publishpark)

        val apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJpc3MiOiJhbHBzIiwic3ViIjoiMmMwZDdmYjQtOGQzNS00MWZkLTk3OTYtMTk4NTE2NzhkNmQxIiwiYXVkIjpbIlB1YmxpYyJdLCJuYmYiOjE2MDAyODI4OTUsImlhdCI6MTYwMDI4Mjg5NSwianRpIjoiMSJ9.jQGnW_JRatpOyCKI6Oa0QgTa3H2psNfb3sXhLk2_VdmPkfAnEaF7BHM0EWwcTkJ-GTNiW9GAw06UuePsQHvFrQ"
        if (!Matchmore.isConfigured())
        {
            Matchmore.config(this, apiKey, false)
        }

        val bigcar_txt = findViewById(R.id.activity_publishpark_bigcar) as EditText
        val availdate_txt: EditText = findViewById(R.id.activity_publishpark_availdate) as EditText
        //val availdate_txt: DatePicker = findViewById(R.id.activity_publishpark_availdate) as DatePicker
        val center_txt = findViewById(R.id.activity_publishpark_center) as EditText
        val transport_txt: EditText = findViewById(R.id.activity_publishpark_transport) as EditText
        val type_txt = findViewById(R.id.activity_publishpark_type) as EditText
        val price_txt = findViewById(R.id.activity_publishpark_price) as EditText
        val availtime_txt: EditText = findViewById(R.id.activity_publishpark_availtime) as EditText
        val submit_btn = findViewById(R.id.activity_publishpark_submit) as Button
        val status_txt = findViewById(R.id.activity_publishpark_status) as TextView

        // Conversion to type SpannableStringBuilder
        val bigcar = bigcar_txt.text
        val availdate = availdate_txt.text
        val availtime = availtime_txt.text
        val center = center_txt.text
        val transport = transport_txt.text
        val type = type_txt.text
        val price = price_txt.text

            submit_btn.setOnClickListener()
        {
            Matchmore.instance.apply {
                startUsingMainDevice({ device ->
                    val pub = Publication("park", 1.0, 86400.0)
                    //pub.properties = hashMapOf("city" to city.toString(), "citycenter" to center.toString(),"typeparking" to type.toString(), "price" to newprice.toInt()
                    val pubHashMap = hashMapOf<String, Any>()
                    pubHashMap["price"] = price.toString().toInt()
                    pubHashMap["availdate"] = availdate.toString()
                    pubHashMap["availtime"] = availtime.toString()
                    pubHashMap["typeparking"] = type.toString()
                    pubHashMap["citycenter"] = center.toString()
                    pubHashMap["transport"] = transport.toString()
                    pubHashMap["bigcar"] = bigcar.toString()
                    pub.properties = pubHashMap


                    createPublicationForMainDevice(
                        pub,
                        { result ->
                            Log.d(
                                "debug",
                                "Publication made  successfully with topic " + pub.topic.toString()
                            )
                            status_txt.setText("Parking availability published")
                        }, Throwable::printStackTrace
                    )
                }, Throwable::printStackTrace)
            }
        }
        checkLocationPermission()
    }
    //Function to check Location
    private fun checkLocationPermission() {
        val permissionListener = object : PermissionListener {
            @SuppressLint("MissingPermission")
            override fun onPermissionGranted() {
                Matchmore.instance.apply {
                    startUpdatingLocation()
                    startRanging()
                }
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                Toast.makeText(this@PublishParkActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }

        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("Permission Denied")
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
            .check()
    }
}
