package com.example.appbanhang2020.dangnhapki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhang2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Dangki_regin extends AppCompatActivity {
    Button btdn;
    EditText txtten,txtmk,txtsdt;
    //thanhtaifirebase
    ProgressDialog loadingbarl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki_regin);
        anhxa();
        cickdangnhap();
    }

    private void cickdangnhap() {
        btdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Danhnhap();
            }
        });

    }

    private void Danhnhap() {
        String ten=txtten.getText().toString();
        String dienthoai=txtsdt.getText().toString();
        String matkhau=txtmk.getText().toString();

        if(TextUtils.isEmpty(ten))
        {
            Toast.makeText(Dangki_regin.this,"Nhập tên vào",Toast.LENGTH_SHORT).show();
        }
        else {
            loadingbarl.setTitle("Dang nhap");
            loadingbarl.setMessage("Ban cho trong giay lat");
            loadingbarl.setCanceledOnTouchOutside(false);
            loadingbarl.show();

            kiemtratrungsdt(ten,dienthoai,matkhau);
        }
    }

    private void kiemtratrungsdt(final String ten, final String dienthoai, final String matkhau)
    {
        final DatabaseReference data;
        data= FirebaseDatabase.getInstance().getReference();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("Nguoi dung").child(dienthoai).exists())
                {
                    HashMap<String,Object> userdataMap=new HashMap<>();
                    userdataMap.put("phone",dienthoai);
                    userdataMap.put("ten",ten);
                    userdataMap.put("matkhau",matkhau);
                    data.child("Nguoi dung").child(dienthoai).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {   loadingbarl.dismiss();
                                        Intent intent=new Intent(Dangki_regin.this,Dangnhap_login.class);
                                        Toast.makeText(Dangki_regin.this,"Bạn đã đăng kí thành công",Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    }
                        }
                    });
                }
                else {
                    Toast.makeText(Dangki_regin.this,"Số điện thoại đã được đăng kí",Toast.LENGTH_SHORT).show();
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
        txtten=findViewById(R.id.tendangnhap);
        txtmk=findViewById(R.id.pass);
        txtsdt=findViewById(R.id.sdt);
        loadingbarl=new ProgressDialog(this);
    }
}
