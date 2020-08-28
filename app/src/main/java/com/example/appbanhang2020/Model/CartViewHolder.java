package com.example.appbanhang2020.Model;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang2020.R;
import com.example.appbanhang2020.ViewHodler.Iteamcick;
import com.rey.material.widget.Spinner;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  public   TextView txtten,txtgia,txtsl;
   public Iteamcick iteamcick;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txtten=itemView.findViewById(R.id.aaaa);
        txtsl=itemView.findViewById(R.id.sl);
        txtgia=itemView.findViewById(R.id.bbb);

    }

    @Override
    public void onClick(View view) {
            iteamcick.onCick(view,getAdapterPosition(),false);
    }
    public void setIteamcick(Iteamcick iteamcick)
    {

    }
}
