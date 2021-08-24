package com.erenduran.maskisudepo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class CreatePlaceActivity extends AppCompatActivity {

    EditText nameText,typeText,atmosphereText;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_place);

        nameText = findViewById(R.id.name_text_create_place_activity);
        typeText = findViewById(R.id.type_text_create_place_activity);
        atmosphereText = findViewById(R.id.atmosphere_text_create_place_activity);
        imageView = findViewById(R.id.imageview_create_place_activity);


    }

    public void next(View view){


        // intent
    }
}