package com.erenduran.maskisudepo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback , GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;

    String latitudeString;
    String longitudeString;
    // string ten double a double dan stringe çevirmek rahat, parse a bu şekilde kaydedilecek


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_place,menu);

        return super.onCreateOptionsMenu(menu);
    }

    // tıklanınca ne olacağı
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.save_place){
           upload();
            // yeni eklenen tesis locations activityde gösterilecek
            Intent intent = new Intent(getApplicationContext(),LocationsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                //get location

                // shared preferences kullanıcı ilk defa açıyorsa bu aktivite çağırılsın, ondan sonra çağırılmasın.
                SharedPreferences sharedPreferences = MapsActivity.this.getSharedPreferences("com.erenduran.maskisudepo",MODE_PRIVATE);
                boolean firstTimeCheck = sharedPreferences.getBoolean("notFirstTime",false);

                //sharedPreferences içinden boolean olarak firstTimeCheck diye bir objeyi alacak, eğer bu daha önceden kayıt edilmedi ise
                //bana false olarak gelecek

                if(!firstTimeCheck){
                    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
                    sharedPreferences.edit().putBoolean("notFirstTime",true).apply();
                }



            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {

            }
        };

         if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else{
            //get location

             locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
             mMap.clear();

             Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

             if(lastKnownLocation != null){
                 LatLng lastUserLocation = new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude());
                 // LatLng enlem boylam
                 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15)); // zoom level 15

             }
         }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 ){
            if (grantResults.length > 0){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //get location

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                    mMap.clear();

                    Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if(lastKnownLocation != null){
                        LatLng lastUserLocation = new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude());
                        // LatLng enlem boylam
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15)); // zoom level 15

                    }
                }
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        Double latitude = latLng.latitude;
        Double longitude = latLng.longitude;

        latitudeString = latitude.toString();
        longitudeString = longitude.toString();

        // kullanıcı nereye tıkladığını görebilsin diye marker
        mMap.addMarker(new MarkerOptions().title("Yeni Tesis").position(latLng));

        Toast.makeText(this,"Tesis Kaydet'e tıkla",Toast.LENGTH_SHORT).show();

    }

    public void upload(){
        // parse upload

        PlacesClass placesClass = PlacesClass.getInstance();
        String placeName = placesClass.getName();
        String placesType = placesClass.getType();
        String placesAtmosphere = placesClass.getAtmosphere();
        Bitmap placeImage = placesClass.getImage();


    }
}