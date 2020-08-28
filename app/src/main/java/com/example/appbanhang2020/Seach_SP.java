package com.example.appbanhang2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.appbanhang2020.Model.SanPham;
import com.example.appbanhang2020.Thanhtoan.Chitietmuasp;
import com.example.appbanhang2020.ViewHodler.ViewHodlerOrder;
import com.example.appbanhang2020.ViewHodler.ViewHodlerSP;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Seach_SP extends AppCompatActivity {
    Button bt1;
    EditText editText;
    RecyclerView recyclerView;
    //dau vao khi tim kiem
    String seach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach__sp);
        anhxa();
        timkiem();
    }

    private void timkiem() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seach=editText.getText().toString();
                onStart();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Products");
        final FirebaseRecyclerOptions<SanPham> options=new FirebaseRecyclerOptions.Builder<SanPham>()
                .setQuery(databaseReference.orderByChild("pname").startAt(seach),SanPham.class).build();

        FirebaseRecyclerAdapter<SanPham, ViewHodlerSP> adapter=new FirebaseRecyclerAdapter<SanPham, ViewHodlerSP>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewHodlerSP viewHodlerSP, int i, @NonNull final SanPham sanPham) {

                viewHodlerSP.txtten.setText(sanPham.getPname());
                viewHodlerSP.txtgia.setText("Gia: " + sanPham.getPrice() + "$");
                viewHodlerSP.txtmota.setText(sanPham.getDescription());
                Picasso.get().load(sanPham.getImage()).into(viewHodlerSP.haa);

                viewHodlerSP.haa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Seach_SP.this, Chitietmuasp.class);
                        intent.putExtra("pid",sanPham.getPid());
                        startActivity(intent);
                    }
                });

            }
            @NonNull
            @Override
            public ViewHodlerSP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_sp ,parent,false);
                ViewHodlerSP viewHodlerSP=new ViewHodlerSP(view);
                return  viewHodlerSP;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void anhxa() {
        bt1=findViewById(R.id.hihi);
        editText=findViewById(R.id.seach_sp1);
        recyclerView=findViewById(R.id.dssanpham);
        recyclerView.setLayoutManager(new LinearLayoutManager(Seach_SP.this));

    }
}
