package com.example.appbanhang2020.ViewHodler;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang2020.R;

public class ViewHodlerSP extends RecyclerView.ViewHolder implements View.OnClickListener {
   public TextView txtten,txtmota,txtgia;
  public   ImageView haa;
    Iteamcick lis;
    public ViewHodlerSP(@NonNull View itemView) {
        super(itemView);
    txtten=itemView.findViewById(R.id.product_name);
    txtmota=itemView.findViewById(R.id.product_description);
        txtgia=itemView.findViewById(R.id.giasp);
    haa=itemView.findViewById(R.id.product_image);
    }
    public void setOnCick(Iteamcick  lis)
    {
        this.lis=lis;
    }

    @Override
    public void onClick(View view) {
        lis.onCick(view,getAdapterPosition(),false);
    }
}
