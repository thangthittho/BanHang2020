package com.example.appbanhang2020.Trangchu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appbanhang2020.R;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Setting_Trangchu extends AppCompatActivity {
    CircleImageView hinhdaidien;
    EditText ten,sdt,diachi;
    TextView txtclose,txtsave,txtupdate;

    private Uri imauri;
    String myuri="";
    private StorageReference storageReference;
    String check="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__trangchu);
        anhxa();
        thongsothaydoi(hinhdaidien,ten,sdt,diachi);
        dongupdade();
    }

    private void dongupdade() {
        close();
        capnhat();

    }

    private void capnhat() {
        txtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check.equals("Click"))
                {
                    luuthongtin();
                }
                else {
                    capnhatthongtin();
                }
            }
        });
    }

    private void capnhatthongtin() {
    }

    private void luuthongtin() {

    }

    private void close() {
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void thongsothaydoi(final CircleImageView hinhdaidien, final EditText ten, final EditText sdt, EditText diachi) {
        DatabaseReference user= FirebaseDatabase.getInstance().getReference().child("Nguoi dung").child(Pre.tructuyen.getPhone());
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //kiem tra anh ton tai
                if(dataSnapshot.exists())
                {
                    if (dataSnapshot.child("image").exists())
                    {
                        String image=dataSnapshot.child("image").getValue().toString();
                        String name=dataSnapshot.child("ten").getValue().toString();
                        String phone=dataSnapshot.child("phone").getValue().toString();
                        String diachi=dataSnapshot.child("diachi").getValue().toString();

                        Picasso.get().load(image).into(hinhdaidien);
                        ten.setText(name);
                        sdt.setText(phone);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void anhxa() {

    }
}
