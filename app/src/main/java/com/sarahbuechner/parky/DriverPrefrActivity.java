package com.sarahbuechner.parky;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Class for driver to insert preferences about lot
public class DriverPrefrActivity extends AppCompatActivity {
    private EditText price_txt;
    private EditText type_txt;
    private EditText largecar_txt;
    private EditText distancepklot_txt;
    private Button submit_btn;
    private TextView status_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_prefr);
        price_txt = (EditText) findViewById(R.id.activity_driver_prefr_price);
        type_txt = (EditText) findViewById(R.id.activity_driver_prefr_type);
        largecar_txt = (EditText) findViewById(R.id.activity_driver_prefr_largecar);
        distancepklot_txt = (EditText) findViewById(R.id.activity_driver_distancepklot);
        submit_btn = (Button) findViewById(R.id.activity_driver_prefr_submit);
        status_txt = (TextView) findViewById(R.id.activity_driver_prefr_status);

        final Editable price;
        price = price_txt.getEditableText();
        final Editable type;
        type = type_txt.getEditableText();
        final Editable largecar;
        largecar = largecar_txt.getEditableText();
        final Editable distpklot;
        distpklot = distancepklot_txt.getEditableText();

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //User clicked the button
                //Will allow us to get data from DriverPrefr activity
                Intent myIntent = new Intent(DriverPrefrActivity.this, SearchParkActivity.class);
                //Creating a bundle
                Bundle bundle = new Bundle();
                //Adding values to bundle
                bundle.putString("driverlargecar", String.valueOf(largecar));
                bundle.putString("drivertype", String.valueOf(type));
                bundle.putString("driverprice", String.valueOf(price));
                bundle.putString("driverdistancepklot", String.valueOf(distpklot));

                myIntent.putExtra("mybundle", bundle);
                startActivity(myIntent);
            }
        });
    }
}