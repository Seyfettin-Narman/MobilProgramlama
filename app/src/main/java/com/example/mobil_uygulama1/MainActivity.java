package com.example.mobil_uygulama1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button converterButton = findViewById(R.id.converterButton);
        Button randomButton = findViewById(R.id.randomButton);
        Button smsButton= findViewById(R.id.smsButton);
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        final Animation animSlideInRight = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);
        final Animation animSlideInUp = AnimationUtils.loadAnimation(this,R.anim.slide_in_up);
        converterButton.setAnimation(animSlideInRight);
        randomButton.setAnimation(animSlideInRight);
        smsButton.setAnimation(animSlideInRight);
        textView.setAnimation(animSlideInUp);
        textView2.setAnimation(animSlideInUp);
        converterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConverterActivity.class);
                startActivity(intent);
            }
        });
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RandomActivity.class);
                startActivity(intent);
            }
        });
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SmsActivity.class);
                startActivity(intent);
            }
        });
    }

}