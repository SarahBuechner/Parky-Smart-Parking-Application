package com.sarahbuechner.parky;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

//Class to display details about matched lots
public class ParkingDetailActivity extends AppCompatActivity {
    private TextView mLargecar;
    private TextView mDistance;
    private TextView mTransport;
    private TextView mTypeParking;
    private TextView mPrice;
    private TextView mAvailDate;
    private TextView mAvailTime;
    private Button mBook;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);
        mPrice = (TextView)findViewById(R.id.activity_parking_detail_price);
        mAvailDate = (TextView) findViewById(R.id.activity_parking_detail_availdate);
        mAvailTime = (TextView) findViewById(R.id.activity_parking_detail_availtime);
        mTypeParking = (TextView) findViewById(R.id.activity_parking_detail_typeparking);
        mDistance = (TextView) findViewById(R.id.activity_parking_detail_center);
        mTransport = (TextView)findViewById(R.id.activity_parking_detail_transport);
        mLargecar = (TextView)findViewById(R.id.activity_parking_detail_largecar);
        mBook = (Button) findViewById(R.id.activity_parking_detail_book);

        //Details about lot (from lot provider) that is a match
        String Price = "";
        Intent intent = getIntent();
        Price = intent.getStringExtra("price");
        mPrice.setText(Price);

        String AvailDate = "";
        AvailDate = intent.getStringExtra("availabdate");
        mAvailDate.setText(AvailDate);

        String AvailTime = "";
        AvailTime = intent.getStringExtra("availabtime");
        mAvailTime.setText(AvailTime);

        String Parkingtype = "";
        Parkingtype = intent.getStringExtra("parktype");
        mTypeParking.setText(Parkingtype);

        String Distance = "";
        Distance = intent.getStringExtra("distance");
        mDistance.setText(Distance);

        String TransportDis = "";
        TransportDis = intent.getStringExtra("transport");
        mTransport.setText(TransportDis);

        String Largecar = "";
        Largecar = intent.getStringExtra("bigcar");
        mLargecar.setText(Largecar);
    }
}