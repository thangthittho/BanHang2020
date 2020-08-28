package com.example.appbanhang2020;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.appbanhang2020.Admin.Checkhome;
import com.example.appbanhang2020.Model.SanPham;
import com.example.appbanhang2020.Thanhtoan.CartSanPham;
import com.example.appbanhang2020.Thanhtoan.Chitietmuasp;
import com.example.appbanhang2020.Trangchu.Setting_Trangchu;
import com.example.appbanhang2020.ViewHodler.ViewHodlerSP;
import com.example.appbanhang2020.dangnhapki.Dangnhap_login;
import com.example.appbanhang2020.dangnhapki.Quenmk.Pre;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivyty extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    String nguoiquanly="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activyty);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null)
        {
            nguoiquanly=getIntent().getExtras().get("Admin").toString();
        }

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Products");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent=new Intent(HomeActivyty.this, CartSanPham.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);

        View haderview=nav.getHeaderView(0);
        TextView tenkhach=haderview.findViewById(R.id.tenkhach);
        CircleImageView ha=haderview.findViewById(R.id.hinhanhkhach);

        if (!nguoiquanly.equals("Admin"))
        {
            tenkhach.setText(Pre.tructuyen.getTen());
        }



        recyclerView=findViewById(R.id.cutnhanh);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseRecyclerOptions<SanPham> options=new FirebaseRecyclerOptions.Builder<SanPham>()
                .setQuery(databaseReference,SanPham.class).build();

        FirebaseRecyclerAdapter<SanPham, ViewHodlerSP> adapter=new FirebaseRecyclerAdapter<SanPham, ViewHodlerSP>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ViewHodlerSP viewHodlerSP, int i, @NonNull final SanPham sanPham) {

                viewHodlerSP.txtten.setText(sanPham.getPname());
                viewHodlerSP.txtgia.setText("Gia: " +sanPham.getPrice()+"$");
                viewHodlerSP.txtmota.setText(sanPham.getDescription());
                Picasso.get().load(sanPham.getImage()).into(viewHodlerSP.haa);

                viewHodlerSP.haa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (nguoiquanly.equals("Admin"))
                        {
                            Intent intent = new Intent(HomeActivyty.this, Checkhome.class);
                            intent.putExtra("pid", sanPham.getPid());
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(HomeActivyty.this, Chitietmuasp.class);
                            intent.putExtra("pid", sanPham.getPid());
                            startActivity(intent);
                        }
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_activyty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            Intent intent=new Intent(HomeActivyty.this, CartSanPham.class);

            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.nav_order) {
            Intent intent=new Intent(HomeActivyty.this, Seach_SP.class);
            startActivity(intent);
        } else if (id == R.id.nav_category) {

        } else if (id == R.id.nav_setting) {
            Intent intent=new Intent(HomeActivyty.this, Setting_Trangchu.class);
            startActivity(intent);

        } else if (id == R.id.nav_thoat) {
            Intent intent=new Intent(HomeActivyty.this, Dangnhap_login.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
