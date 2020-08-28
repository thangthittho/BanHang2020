package com.example.appbanhang2020.dangnhapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.appbanhang2020.Admin.ThemspAdmin;
import com.example.appbanhang2020.HomeActivyty;
import com.example.appbanhang2020.Model.Users;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Button btdn,btdangki;
    ProgressDialog loadingbarl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        Paper.init(this);
        danhnhapkich();
        chondangki();

        tudangnhap();

    }

    private void tudangnhap() {
        String UserPhoneKey = Paper.book().read(Pre.sdtkey);
        String UserPasswordKey = Paper.book().read(Pre.passkey);

        if(UserPhoneKey !=""&&UserPasswordKey !="")

        {
            if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {
                kiemtradangnhap(UserPhoneKey, UserPasswordKey);
                loadingbarl.setTitle("Already Logged in");
                loadingbarl.setMessage("Bạn đợi trong giây lát.....");
                loadingbarl.setCanceledOnTouchOutside(false);
                loadingbarl.show();
            }
        }
    }

    private void chondangki() {
        btdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Dangki_regin.class);
                startActivity(i);
            }
        });
    }

    private void danhnhapkich() {
        btdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Dangnhap_login.class);
                startActivity(i);
            }
        });
    }

    private void anhxa() {
        btdn=findViewById(R.id.dangnhap);
        btdangki=findViewById(R.id.dangki);
        loadingbarl=new ProgressDialog(this);
    }

    private void kiemtradangnhap(final String dienthoai, final String matkhau) {
        final DatabaseReference data;
        data= FirebaseDatabase.getInstance().getReference();
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Nguoi dung").child(dienthoai).exists()) {
                    Users users = dataSnapshot.child("Nguoi dung").child(dienthoai).getValue(Users.class);

                    if (users.getPhone().equals(dienthoai)) {
                        if (users.getMatkhau().equals(matkhau)) {
                                Intent intent = new Intent(MainActivity.this, HomeActivyty.class);
                                Pre.tructuyen= users;
                                startActivity(intent);

                        }
                        else
                        {
                            loadingbarl.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else {
                    loadingbarl.dismiss();
                    Toast.makeText(MainActivity.this,"aaaaaaaaa",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
