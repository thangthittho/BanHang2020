package com.example.appbanhang2020.Thanhtoan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang2020.Model.Cart;
import com.example.appbanhang2020.Model.CartViewHolder;
import com.example.appbanhang2020.Model.SanPham;
import com.example.appbanhang2020.R;
import com.example.appbanhang2020.User.Thongtinnguoimuahang;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class CartSanPham extends AppCompatActivity {
    TextView txttien,txtmas;
    RecyclerView list;
    Button btnext;
    int tong=0;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_san_pham);

        anhxa();
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txttien.setText("tongtien= "+String.valueOf(tong));
                Intent intent=new Intent(CartSanPham.this, Thongtinnguoimuahang.class);
                intent.putExtra("tongtien",String.valueOf(tong));
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
       CheckHang();
        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(cartlistref.
                child("Nguoi mua xem").child(Pre.tructuyen.getPhone()).child("Products"), Cart.class).build();
        FirebaseRecyclerAdapter<Cart,CartViewHolder> adapter=new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int postions, @NonNull final Cart cart) {
                holder.txtten.setText("Ten hang: "+cart.getPname());
                holder.txtgia.setText("Giá bán: "+cart.getPrice());
                holder.txtsl.setText("Số lượng: "+cart.getSoluong());

                int thanhtien=((Integer.valueOf(cart.getPrice())))* Integer.valueOf(cart.getSoluong());

                tong=tong+thanhtien;
                txttien.setText("tongtien= "+String.valueOf(tong));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[]=new CharSequence[]
                                {
                                  "Edit",
                                  "Delete"
                                };
                        AlertDialog.Builder builder=new AlertDialog.Builder(CartSanPham.this);
                        builder.setTitle("Cart Options");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                if (i==0)
                                {
                                    Intent intent=new Intent(CartSanPham.this,Chitietmuasp.class);
                                    intent.putExtra("pid",cart.getPid());
                                    startActivity(intent);
                                }
                                if (i==1)
                                {
                                   cartlistref.child("Nguoi mua xem").child(Pre.tructuyen.getPhone()).child("Products").child(cart.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(CartSanPham.this,"Xóa hàng mua thành công",Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(CartSanPham.this,CartSanPham.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        builder.show();

                    }
                });
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_iteam,parent,false);
                CartViewHolder holder=new CartViewHolder(view);
                return holder;
            }
        };

        list.setAdapter(adapter);
        adapter.startListening();
    }

    private void CheckHang() {
        final DatabaseReference data=FirebaseDatabase.getInstance().getReference().child("Order").child(Pre.tructuyen.getPhone());
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String shipperdc = dataSnapshot.child("shipp").getValue().toString();
                    String shipperten = dataSnapshot.child("pname").getValue().toString();
                    if (shipperdc.equals("co shipp")) {

                        txttien.setText(shipperten + "Sản phẩm đang được shipp");
                        list.setVisibility(View.GONE);
                        txtmas.setVisibility(View.VISIBLE);
                        txttien.setText("Don hang cua ban da duoc shipp");
                        btnext.setVisibility(View.GONE);

                    } else if (shipperdc.equals("ko shipp")) {
                        txttien.setText("Tong tien= chua shipp");
                        list.setVisibility(View.GONE);
                        txtmas.setVisibility(View.VISIBLE);

                        btnext.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void anhxa() {
        txttien=findViewById(R.id.tongtien);
        txtmas=findViewById(R.id.kiemtrahang);
        btnext=findViewById(R.id.next);
        list=findViewById(R.id.cartlist);
        list.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

    }

}
