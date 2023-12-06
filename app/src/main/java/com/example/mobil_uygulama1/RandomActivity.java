package com.example.mobil_uygulama1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class RandomActivity extends AppCompatActivity {
    private EditText adetInput,minInput,maxInput;
    private LinearLayout progressBarContainer;
    private Button generateButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        adetInput=findViewById(R.id.adetInput);
        minInput=findViewById(R.id.minInput);
        maxInput=findViewById(R.id.maxInput);
        progressBarContainer=findViewById(R.id.progressBarContainer);
        generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandom();
            }
        });
    }
    private void generateRandom(){
        try{
            progressBarContainer.removeAllViews();
            int adet = Integer.parseInt(adetInput.getText().toString());
            int min = Integer.parseInt(minInput.getText().toString());
            int max = Integer.parseInt(maxInput.getText().toString());
            if(min>max){
                return;
            }
            for(int i=0;i<adet;i++){
                addProgressBar(min,max);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
    private void addProgressBar(int min,int max){
        LinearLayout progressBarLayout = new LinearLayout(this);
        progressBarLayout.setOrientation(LinearLayout.HORIZONTAL);
        ProgressBar progressBar = new ProgressBar(this,null, android.R.attr.progressBarStyleHorizontal);
        int newMax=0,newMin=0;
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(
                350,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        ((LinearLayout.LayoutParams)progressBar.getLayoutParams()).gravity= Gravity.CENTER_HORIZONTAL;
        Random rnd = new Random();
        int randomValue1=rnd.nextInt((max-min)+1)+min;
        int randomValue2 = rnd.nextInt((max-min)+1)+min;
        if(randomValue2>randomValue1){
            newMax=randomValue2;
            newMin=randomValue1;
        }else if(randomValue2<randomValue1){
            newMax=randomValue1;
            newMin=randomValue2;
        }else{
            newMax=randomValue1+1;
            newMin=randomValue2;
        }
        TextView minTextview = new TextView(this);
        minTextview.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        minTextview.setText("Min:" + newMin);
        progressBarLayout.addView(minTextview);
        int progress = rnd.nextInt((newMax-newMin)+1)+newMin;
        int percentage = (progress - newMin)*100/(newMax-newMin);
        progressBar.setProgress(percentage);
        progressBarLayout.addView(progressBar);
        progressBarContainer.addView(progressBarLayout);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                350,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        ((LinearLayout.LayoutParams)textView.getLayoutParams()).gravity=Gravity.CENTER_HORIZONTAL;
        textView.setText("Value: " + progress+",percentage: "+percentage+"%");
        progressBarContainer.addView(textView);
        TextView maxTextView = new TextView(this);
        maxTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        maxTextView.setText("Max: "+newMax);
        progressBarLayout.addView(maxTextView);
        ViewGroup parent = (ViewGroup) progressBarLayout.getParent();
        if(parent !=null){
            parent.removeView(progressBarLayout);
        }
        progressBarContainer.addView(progressBarLayout);
        addLineView();
    }
    private void addLineView(){
        View lineView =new View(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2
        );
        layoutParams.setMargins(0,10,0,20);
        lineView.setLayoutParams(layoutParams);
        lineView.setBackgroundColor(Color.WHITE);
        progressBarContainer.addView(lineView);
    }

}
