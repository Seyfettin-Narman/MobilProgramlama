package com.example.mobil_uygulama1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SmsActivity extends AppCompatActivity {
    private TextInputEditText telInput,mesajInput;
    private Button smsGonder;
    private static final int SMS_PERMISSION_REQUEST_CODE=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        telInput=findViewById(R.id.telInput);
        mesajInput=findViewById(R.id.mesajInput);
        smsGonder=findViewById(R.id.smsGonder);
        smsGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = telInput.getText().toString();
                String message = mesajInput.getText().toString();

                if (!phoneNumber.isEmpty() && !message.isEmpty()) {
                    if (checkSmsPermission()) {

                       sendSms(phoneNumber,message);
                       //jsondan veri gelmesini istiyorsak:
                        //ServiceMessage(phoneNumber,message);
                    } else {
                        // SMS izni yoksa izin talep et
                        requestSmsPermission();
                    }
                } else {
                    Toast.makeText(SmsActivity.this, "Lütfen telefon numarası ve mesaj giriniz.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkSmsPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestSmsPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
    }
    private void sendSms(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(SmsActivity.this, "SMS gönderildi", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(SmsActivity.this, "SMS gönderilirken hata oluştu", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void ServiceMessage(final String phoneNumber,final String message){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuoteService quoteService = retrofit.create(QuoteService.class);
        Call<Quote> call = quoteService.getQuote();
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if(response.isSuccessful()){
                    Quote quote = response.body();
                    String alinti = quote.getBody();
                    sendSms(phoneNumber,alinti);
                }else{
                    Toast.makeText(SmsActivity.this,"Alıntı alınamadı",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == SMS_PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                String phoneNumber = telInput.getText().toString();
                String message = mesajInput.getText().toString();
                sendSms(phoneNumber,message);
            }else{
                Toast.makeText(SmsActivity.this,"SMS gönderme izni reddedildi",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
