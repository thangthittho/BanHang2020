package com.example.appbanhang2020.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.appbanhang2020.R;

public class Checkhome extends AppCompatActivity {
        EditText e1,e2,e3;
        Button b1;
        ImageView un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkhome);
        anhxa();
    }

    private void anhxa() {
        un=findViewById(R.id.sp_ha);
        e1=findViewById(R.id.sp_ten);
        e2=findViewById(R.id.sp_gia);
        e3=findViewById(R.id.sp_mota);
        b1=findViewById(R.id.sp_apdung);
    }
}
