package com.example.mobil_uygulama1;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConverterActivity extends AppCompatActivity {
    private Spinner spinnerDonusturucu,spinnerMegaByte;
    private ArrayAdapter<CharSequence> adapterDonusturucu,adapterMegaByte;
    private EditText donusturucuInput,megaByteInput, sicaklikInput;
    private TextView donusturucuResult,megaByteResult,sicaklikResult;
    private Button donusturucuButton,megaByteButton,sicaklikButton;
    private RadioGroup radioGroup;
    private void Init(){
        spinnerDonusturucu = findViewById(R.id.spinnerDonusturucu);
        spinnerMegaByte = findViewById(R.id.spinnerMegaByte);
        donusturucuInput = findViewById(R.id.donusturucuInput);
        megaByteInput=findViewById(R.id.megaByteInput);
        sicaklikInput = findViewById(R.id.sicaklikInput);
        donusturucuResult = findViewById( R.id.donusturucuResult);
        megaByteResult = findViewById(R.id.megaByteResult);
        sicaklikResult = findViewById(R.id.sicaklikResult);
        donusturucuButton = findViewById(R.id.donusturucuButton);
        megaByteButton=findViewById(R.id.megaByteButton);
        sicaklikButton = findViewById(R.id.sicaklikButton);
        radioGroup = findViewById(R.id.radioGroup);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        Init();
        adapterDonusturucu = ArrayAdapter.createFromResource(this,R.array.DonusturList, android.R.layout.simple_spinner_item);
        adapterDonusturucu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDonusturucu.setAdapter(adapterDonusturucu);
        adapterMegaByte = ArrayAdapter.createFromResource(this,R.array.ByteList, android.R.layout.simple_spinner_item);
        adapterMegaByte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMegaByte.setAdapter(adapterMegaByte);
        donusturucuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = donusturucuInput.getText().toString();
                try{
                    String selectedUtils = spinnerDonusturucu.getSelectedItem().toString();
                    String result="";
                    if(selectedUtils.equals("ikilik")){
                        result = Integer.toBinaryString(Integer.parseInt(inputValue));
                    }else if(selectedUtils.equals("sekizlik")){
                        result = Integer.toOctalString(Integer.parseInt(inputValue));
                    }else if(selectedUtils.equals("onaltılık")){
                        result = Integer.toHexString(Integer.parseInt(inputValue));
                    }
                    donusturucuResult.setText(result);
                }catch (NumberFormatException e){
                    donusturucuResult.setText("geçerli sayı girin");
                }
            }
        });
        megaByteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String megaByteValue = megaByteInput.getText().toString();
                try{
                    double result=0;
                    double megabyte = Double.parseDouble(megaByteValue);
                    String SelectedUtil = spinnerMegaByte.getSelectedItem().toString();
                    switch (SelectedUtil){
                        case "Kilo Byte":
                            result= megabyte * 1024;
                            break;
                        case "Byte":
                            result = megabyte * 1024*1024;
                            break;
                        case "Kibi Byte":
                            result = megabyte * 1000;
                            break;
                        case "Bit":
                            result = megabyte * 8 * 1024 * 1024;
                            break;
                    }
                    megaByteResult.setText(String.valueOf(result));
                }catch (NumberFormatException e ){
                    megaByteResult.setText("geçerli sayı girin");
                }
            }
        });
        sicaklikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertSicaklik();
            }
        });
    }
    private void convertSicaklik(){
        String sicaklikValue = sicaklikInput.getText().toString();
        if(sicaklikValue.isEmpty()){
            sicaklikInput.setError("bo bırakılamaz");
        }
        try {
            int SelectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            RadioButton SelectedRadioButton = findViewById(SelectedRadioButtonId);
            double result = 0;
            double celcius = Double.parseDouble(sicaklikValue);
            if (SelectedRadioButtonId != -1) {
                if (SelectedRadioButton.getId() == R.id.FahrenaytDonustur) {
                    result = celcius * 9 / 5 + 32;
                } else if (SelectedRadioButton.getId() == R.id.KelvinDonustur) {
                    result = celcius + 273.15;
                }
                sicaklikResult.setText(String.valueOf(result));
            } else {
                Toast.makeText(this, "lütfen kelvin ya da fahrenaytı seç", Toast.LENGTH_SHORT).show();
            }
        }catch (NumberFormatException e){
            sicaklikResult.setText("geçerli sayı giriniz");
        }
    }
}
