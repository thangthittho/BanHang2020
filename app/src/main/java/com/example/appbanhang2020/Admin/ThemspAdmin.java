package com.example.appbanhang2020.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appbanhang2020.HomeActivyty;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.dangnhapki.MainActivity;

public class ThemspAdmin extends AppCompatActivity {
    ImageView a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12;
    Button btcheck,btthoat,btqualy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsp_admin);
        anhxa();
        kichchon();

    }

    private void kichchon() {
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","ao phong");
                startActivity(intent);
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","the thao");
                startActivity(intent);
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","vay ngan");
                startActivity(intent);
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","gile");
                startActivity(intent);
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","ao lot");
                startActivity(intent);
            }
        });
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","tui xach");
                startActivity(intent);
            }
        });
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","mu ");
                startActivity(intent);
            }
        });
        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","giay dep");
                startActivity(intent);
            }
        });
        a9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","tai nghe");
                startActivity(intent);
            }
        });
        a10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","may tinh");
                startActivity(intent);
            }
        });
        a11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","dong ho");
                startActivity(intent);
            }
        });
        a12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this,Themchitietsp.class);
                intent.putExtra("category","dien thoai");
                startActivity(intent);
            }
        });
        btthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this, Checkshipp.class);
                startActivity(intent);
            }
        });
        btqualy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemspAdmin.this, HomeActivyty.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        a1=findViewById(R.id.aophong);
        a2=findViewById(R.id.thethao);
        a3=findViewById(R.id.vayngan);
        a4=findViewById(R.id.gile);
        a5=findViewById(R.id.aolot);
        a6=findViewById(R.id.tuixach);
        a7=findViewById(R.id.mu);
        a8=findViewById(R.id.giaydep);
        a9=findViewById(R.id.tainghe);
        a10=findViewById(R.id.maytinh);
        a11=findViewById(R.id.dongho);
        a12=findViewById(R.id.dienthoai);
        btcheck=findViewById(R.id.check_order);
        btthoat=findViewById(R.id.logout);
        btqualy=findViewById(R.id.team);


    }
}
