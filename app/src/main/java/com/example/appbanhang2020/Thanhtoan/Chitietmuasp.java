package com.example.appbanhang2020.Thanhtoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.appbanhang2020.Admin.Themchitietsp;
import com.example.appbanhang2020.Admin.ThemspAdmin;
import com.example.appbanhang2020.HomeActivyty;
import com.example.appbanhang2020.Model.SanPham;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Chitietmuasp extends AppCompatActivity {
    TextView txtten,txtmota,txtgia;
    ElegantNumberButton bttang;
    ImageView hinhsp;
    Button bt;

    String sanphamid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietmuasp);
        sanphamid=getIntent().getStringExtra("pid");
        laythongtinsp(sanphamid);
        anhxa();
        muasanpham();
    }

    private void muasanpham() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themdsmua();

            }
        });
    }

    private void themdsmua() {

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
       String saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
       String saveCurrentTime = currentTime.format(calendar.getTime());

        String productRandomKey = saveCurrentDate +" "+ saveCurrentTime;
     final DatabaseReference data=FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> cart=new HashMap<>();
        cart.put("pid",sanphamid);
        cart.put("pname",txtten.getText().toString());
        cart.put("price",txtgia.getText().toString());
        cart.put("date",saveCurrentDate);
        cart.put("time",saveCurrentTime);
        cart.put("soluong",bttang.getNumber());
        cart.put("giamgia","");

        data.child("Nguoi mua xem").child(Pre.tructuyen.getPhone()).child("Products").child(sanphamid).updateChildren(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    data.child("Admin xem").child(Pre.tructuyen.getPhone()).child("Products").child(sanphamid).updateChildren(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(Chitietmuasp.this,"thanh cong",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Chitietmuasp.this, HomeActivyty.class);
                                startActivity(intent);

                            }
                        }
                    });
                }
            }
        });
    }

    private void laythongtinsp(String sanphamid) {
        DatabaseReference data= FirebaseDatabase.getInstance().getReference().child("Products");
        data.child(sanphamid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //neuhinhspcosan
                if (dataSnapshot.exists())
                {
                    SanPham pre=dataSnapshot.getValue(SanPham.class);
                    txtten.setText(pre.getPname());
                    txtmota.setText(pre.getDescription());
                    txtgia.setText(pre.getPrice());
                    Picasso.get().load(pre.getImage()).into(hinhsp);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void anhxa() {
         txtten=findViewById(R.id.tenmuasp);
         txtmota=findViewById(R.id.motamuasp);
         txtgia=findViewById(R.id.giamuasp);
         bttang=findViewById(R.id.them);
         hinhsp=findViewById(R.id.hinhmua);
         bt=findViewById(R.id.btmua);
    }
}
