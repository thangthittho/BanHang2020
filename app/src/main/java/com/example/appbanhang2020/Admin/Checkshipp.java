package com.example.appbanhang2020.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.appbanhang2020.Model.Admin_order;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.ViewHodler.ViewHodlerOrder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Checkshipp extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkshipp);
        anhxa();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Admin_order> options = new FirebaseRecyclerOptions.Builder<Admin_order>().setQuery(databaseReference, Admin_order.class).build();
        FirebaseRecyclerAdapter<Admin_order, ViewHodlerOrder> adapter = new FirebaseRecyclerAdapter<Admin_order,ViewHodlerOrder>(options) {
            @NonNull
            @Override
            public ViewHodlerOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oder_admin,parent,false);
               ViewHodlerOrder hodel=new ViewHodlerOrder(view);
                return hodel;
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHodlerOrder hodel,final int postions, @NonNull Admin_order admin_order) {
                hodel.txtten.setText("Ten khach: "+ admin_order.getPname());
                hodel.txtgia.setText("Gia: "+ admin_order.getTongtien());
                hodel.txtsdt.setText("So dien thoai: "+ admin_order.getPhone());
                hodel.txtshipp.setText("Dia chi: "+ admin_order.getDiachi());
                hodel.txtdate.setText("Ngay: "+ admin_order.getDate());

                hodel.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final CharSequence options[]=new CharSequence[]
                                {
                                        "Yes",
                                        "No"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(Checkshipp.this);
                        builder.setTitle("Ban co muon shipp don hang?");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    if (i==0)
                                    {
                                        String abc=getRef(postions).getKey();
                                        Xoa(abc);
                                    }
                                    if (i==1)
                                    {
                                        finish();
                                    }
                            }
                        });
                        builder.show();
                    }
                });


            }
        };
            recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void Xoa(String abc) {
            databaseReference.child(abc).removeValue();
    }

    private void anhxa() {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Order");
        recyclerView=findViewById(R.id.cartlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
