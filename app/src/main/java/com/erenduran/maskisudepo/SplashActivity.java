package com.erenduran.maskisudepo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread logoAnimation = new Thread(){
            @Override
            public void run(){
                ImageView logo = findViewById(R.id.pictureLogo);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_logo);
                logo.startAnimation(animation);
            }
        };
        logoAnimation.start();

        Thread titleAnimation = new Thread(){
            @Override
            public void run(){
                TextView logo = findViewById(R.id.labelTitle);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_logo);
                logo.startAnimation(animation);
            }
        };
        titleAnimation.start();

        Thread authorAnimation = new Thread(){
            @Override
            public void run(){
                TextView author = findViewById(R.id.labelAuthor);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_intro_author);
                author.startAnimation(animation);
            }
        };
        authorAnimation.start();

        Thread redirect = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3500);
                    Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        redirect.start();
    }
}