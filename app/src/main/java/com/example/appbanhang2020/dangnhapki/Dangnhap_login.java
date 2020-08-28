package com.example.appbanhang2020.dangnhapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang2020.Admin.ThemspAdmin;
import com.example.appbanhang2020.HomeActivyty;
import com.example.appbanhang2020.Model.Users;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.Trangchu.Trangchu;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class Dangnhap_login extends AppCompatActivity {
    Button btdn;
    TextView txtadmin, no;
    EditText txtsdt, txtmk;
    ProgressDialog loadingbarl;
    private String cut = "Nguoi dung";
    CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap_login);
        anhxa();
        Paper.init(this);
        kichdangnhap();
        Quantri();



    }

    private void Quantri() {
        Nguoiqual();
        Noquantri();
    }

    private void Noquantri() {
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btdn.setText("Login");
                txtadmin.setVisibility(View.VISIBLE);
                no.setVisibility(View.INVISIBLE);
                cut = "Nguoi dung";
            }
        });
    }

    private void Nguoiqual() {
        txtadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btdn.setText("Login Admin");
                txtadmin.setVisibility(View.INVISIBLE);
                no.setVisibility(View.VISIBLE);
                cut = "Admins";
            }
        });
    }


    private void kichdangnhap() {
        btdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dangnhap();
                //Intent intent = new Intent(Dangnhap_login.this, ThemspAdmin.class);
               // startActivity(intent);
            }
        });
    }


    private void dangnhap() {
        String dienthoai=txtsdt.getText().toString();
        String matkhau=txtmk.getText().toString();
        if(TextUtils.isEmpty(dienthoai))
        {
            Toast.makeText(Dangnhap_login.this,"Nhập tên vào",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(matkhau))
        {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingbarl.setTitle("Dangnhap");
            loadingbarl.setMessage("Ban cho trong giay lat");
            loadingbarl.setCanceledOnTouchOutside(false);
            loadingbarl.show();

            kiemtradangnhap(dienthoai,matkhau);
        }
    }

    private void kiemtradangnhap(final String dienthoai, final String matkhau) {
        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Pre.sdtkey,dienthoai);
            Paper.book().write(Pre.passkey,matkhau);
        }

        final DatabaseReference data=FirebaseDatabase.getInstance().getReference();

       data.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.child(cut).child(dienthoai).exists()) {
                   Users usersdata = dataSnapshot.child(cut).child(dienthoai).getValue(Users.class);

                   if (usersdata.getPhone().equals(dienthoai)) {
                       if (usersdata.getMatkhau().equals(matkhau)) {
                           if (cut.equals("Admins"))
                           {
                               Toast.makeText(Dangnhap_login.this, "Chào mừng người quản lý...", Toast.LENGTH_SHORT).show();
                               loadingbarl.dismiss();
                               Intent intent = new Intent(Dangnhap_login.this, ThemspAdmin.class);
                               startActivity(intent);
                           }
                        else if (cut.equals("Nguoi dung"))
                           {
                               Intent intent = new Intent(Dangnhap_login.this, HomeActivyty.class);
                               Pre.tructuyen= usersdata;
                               startActivity(intent);
                           }

                       }
                       else {
                           loadingbarl.dismiss();
                           Toast.makeText(Dangnhap_login.this,"Loi roi xem lai",Toast.LENGTH_SHORT).show();
                       }
                   }
               }
               else {
                   Toast.makeText(Dangnhap_login.this, "Tài khoản  " + dienthoai+ "không tồn tại", Toast.LENGTH_SHORT).show();
                   loadingbarl.dismiss();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

    private void anhxa() {
        btdn=findViewById(R.id.dangnhap);
        txtsdt=findViewById(R.id.tendangnhap);
        txtmk=findViewById(R.id.pass);
        txtadmin=findViewById(R.id.admin);
        no=findViewById(R.id.noadmin);
        loadingbarl=new ProgressDialog(this);
        chkBoxRememberMe = findViewById(R.id.idcheckbox);


    }
}
