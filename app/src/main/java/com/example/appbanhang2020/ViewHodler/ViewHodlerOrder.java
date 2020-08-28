package com.example.appbanhang2020.ViewHodler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang2020.R;

public class ViewHodlerOrder extends RecyclerView.ViewHolder implements View.OnClickListener{
  public   TextView txtten,txtsdt,txtgia,txtshipp,txtdate;
    public ViewHodlerOrder(@NonNull View itemView) {
        super(itemView);
        txtten=itemView.findViewById(R.id.txtten);
        txtsdt=itemView.findViewById(R.id.sdt);
        txtgia=itemView.findViewById(R.id.khoqua);
        txtshipp=itemView.findViewById(R.id.quequan);
        txtdate=itemView.findViewById(R.id.thoigian);

    }

    @Override
    public void onClick(View view) {

    }
}
