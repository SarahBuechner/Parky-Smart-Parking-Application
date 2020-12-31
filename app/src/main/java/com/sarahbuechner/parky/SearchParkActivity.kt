package com.sarahbuechner.parky

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import io.matchmore.sdk.Matchmore
import io.matchmore.sdk.api.models.Subscription

//Class gets details about driver preferences and checks Matches
class SearchParkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchpark)

        val apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJFUzI1NiJ9.eyJpc3MiOiJhbHBzIiwic3ViIjoiMmMwZDdmYjQtOGQzNS00MWZkLTk3OTYtMTk4NTE2NzhkNmQxIiwiYXVkIjpbIlB1YmxpYyJdLCJuYmYiOjE2MDAyODI4OTUsImlhdCI6MTYwMDI4Mjg5NSwianRpIjoiMSJ9.jQGnW_JRatpOyCKI6Oa0QgTa3H2psNfb3sXhLk2_VdmPkfAnEaF7BHM0EWwcTkJ-GTNiW9GAw06UuePsQHvFrQ"

        if (!Matchmore.isConfigured())
        {
            Matchmore.config(this, apiKey, false)
        }
        checkLocationPermission()
        addSub()
        checkMatches()
    }
    private fun checkMatches() {

        //Declare our ListView
        val listView = findViewById(R.id.activity_searchpark_list) as ListView

        // Empty Array that will be used to store the properties of the publications
        val rsl: ArrayList<String> = ArrayList()
        val pric: ArrayList<String> = ArrayList()
        val date: ArrayList<String> = ArrayList()
        val time: ArrayList<String> = ArrayList()
        val park: ArrayList<String> = ArrayList()
        val centr: ArrayList<String> = ArrayList()
        val transp: ArrayList<String> = ArrayList()
        val big: ArrayList<String> = ArrayList()

        Log.d("debug", "Check matches")

        Matchmore.instance.apply {
            //Start fetching matches
            matchMonitor.addOnMatchListener { matches, _ ->

                //We should get there every time a match occurs
                Log.d("debug", "We got ${matches.size} matches")

                //To get match info for ParkingDetailActivity
                for (i in matches) {
                    pric.add(i.publication!!.properties["price"].toString())
                    date.add(i.publication!!.properties["availdate"].toString())
                    time.add(i.publication!!.properties["availtime"].toString())
                    park.add(i.publication!!.properties["typeparking"].toString())
                    centr.add(i.publication!!.properties["citycenter"].toString())
                    transp.add(i.publication!!.properties["transport"].toString())
                    big.add(i.publication!!.properties["bigcar"].toString())

                    //To get Publication ID
                    Log.d("MAtch:IdOfPublication", i.publication!!.id.toString())

                    //To see all matches in ListView
                    rsl.add("Price: " + i.publication!!.properties["price"].toString() + "CHF/h, Available until: " + i.publication!!.properties["availdate"].toString() + ", To center: " + i.publication!!.properties["citycenter"].toString() + "m, To transport: " + i.publication!!.properties["transport"].toString() + "m")
                }

                val adapter = ArrayAdapter(
                    this@SearchParkActivity, android.R.layout.simple_list_item_1, rsl
                )
                listView.adapter = adapter
            }
            matchMonitor.startPollingMatches(1000)
        }

        //Clickable Item List
        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position, id: Long ->
            //Will show text with content of the variable called position
            Toast.makeText(this, "Clicked item :" + " " + position, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ParkingDetailActivity::class.java)
            val priceInt =  pric.get(position).toString()
            intent.putExtra("price", priceInt)
            intent.putExtra("availabdate", date.get(position))
            intent.putExtra("availabtime", time.get(position))
            intent.putExtra("parktype", park.get(position))
            intent.putExtra("distance", centr.get(position))
            intent.putExtra("transport", transp.get(position))
            intent.putExtra("bigcar", big.get(position))

            this.startActivity(intent);
        }
    }

    //Subscription
    private fun addSub()
    {
        Matchmore.instance.apply {
            startUsingMainDevice({ d ->

                //To get Device ID
                Log.d("MyDeviceID", d.id.toString())

                //Getting values from driver preference activity
                val myIntent: Intent = intent
                val myBundle: Bundle
                myBundle = myIntent.getBundleExtra("mybundle")!!

                //To put driver info into the bundle
                val drivprice: String? = myBundle.getString("driverprice")
                val drivtype: String? = myBundle.getString("drivertype")
                val drivlargecar: String? = myBundle.getString("driverlargecar")
                val distanceparkinglot: String? = myBundle.getString("driverdistancepklot")

                //Conversion distanceparkinglot to Double
                val driverRange: Double? = distanceparkinglot?.toDouble()

                //Conversion of drivprice to Integer
                val driverprice= drivprice?.toInt()


                //The Subscription with filters
                val sub = Subscription("park", driverRange, 180.0,
                    selector = "price < ${driverprice} AND bigcar='${drivlargecar.toString()}' AND typeparking='${drivtype.toString()}'"
                )
                createSubscriptionForMainDevice(sub, { result ->
                    Log.d("debug", "Subscription made successfully with topic ${result.topic}")

                }, Throwable::printStackTrace)
            }, Throwable::printStackTrace)
        }
    }
    //Function to check location
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
                Toast.makeText(this@SearchParkActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("Permission Denied")
            .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .check()
    }
}

