package com.erenduran.maskisudepo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView nameText, typeText, atmosphereText;
    ImageView imageView;
    String placeName;
    String latitudeString;
    String longitudeString;
    Double latitude;
    Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameText = findViewById(R.id.name_text_detail_activity);
        typeText = findViewById(R.id.type_text_detail_activity);
        atmosphereText = findViewById(R.id.atmosphere_text_detail_activity);
        imageView = findViewById(R.id.imageview_detail_activity);

        Intent intent = getIntent();
        placeName = intent.getStringExtra("name");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapDetail);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        getData();// harita hazır olunca çağırmamız lazım, onCreate altında almak saçma
    }

    public void getData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Places");
        query.whereEqualTo("name",placeName);// ismi = placeName olanları çek
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    if(objects.size()>0){

                    }
                }
            }
        });
    }
}