package com.example.smartrestaurentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView bgapp;
    Animation bganim;
    LinearLayout textsplash;
    Button btnqrcode,btnorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgapp=(ImageView) findViewById(R.id.imageview1);
        textsplash=(LinearLayout) findViewById(R.id.textsplash);
        btnqrcode=(Button) findViewById(R.id.btnqrcode);
        btnorder=(Button) findViewById(R.id.btnorder);

        bganim= AnimationUtils.loadAnimation(this,R.anim.bganim);
        bgapp.animate().translationY(-2200).setDuration(800).setStartDelay(300);
        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);

        btnqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), QRGenerator.class);
                startActivity(intent);
            }
        });

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),Orders.class);
                startActivity(intent);
            }
        });
    }
}