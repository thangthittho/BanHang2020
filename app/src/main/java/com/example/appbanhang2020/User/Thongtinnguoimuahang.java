package com.example.appbanhang2020.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhang2020.HomeActivyty;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Thongtinnguoimuahang extends AppCompatActivity {
    EditText txtten,txtsdt,txtdiachi;
    Button btht;
    String tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinnguoimuahang);
        tongtien=getIntent().getStringExtra("tongtien");
        anhxa();
        hoanthanh();
    }

    private void hoanthanh() {
        btht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtten.getText().toString()))
                {
                    Toast.makeText(Thongtinnguoimuahang.this,"Bạn chưa nhập tên của mình vào",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    congvieccuoicung();
                }
            }
        });
    }

    private void congvieccuoicung() {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        String saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        String saveCurrentTime = currentTime.format(calendar.getTime());

        String productRandomKey = saveCurrentDate +" "+ saveCurrentTime;
        final DatabaseReference data= FirebaseDatabase.getInstance().getReference().child("Order")
                .child(Pre.tructuyen.getPhone());
        final HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("tongtien",tongtien);
        hashMap.put("pname",txtten.getText().toString());
        hashMap.put("phone",txtsdt.getText().toString());
       hashMap.put("date",saveCurrentDate);
        hashMap.put("time",saveCurrentTime);
        hashMap.put("shipp","ko shipp");
        hashMap.put("diachi",txtdiachi.getText().toString());

        data.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("Cart List").child("Nguoi dung xem").child(Pre.tructuyen.getPhone())
                            .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                       Toast.makeText(Thongtinnguoimuahang.this,"Bạn đã đặt hàng thành công",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Thongtinnguoimuahang.this, HomeActivyty.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

    }

    private void anhxa() {
        txtdiachi=findViewById(R.id.diachi);
        txtsdt=findViewById(R.id.sodt);
        txtten=findViewById(R.id.ten);
        btht=findViewById(R.id.ok);
    }
}
