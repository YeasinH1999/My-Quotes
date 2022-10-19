package com.yeasinhproject.myquotes;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tvQuotes;
    TextView tvAuthor;
    Button btnCopy,btnShare;
    RelativeLayout relLay;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        tvQuotes = itemView.findViewById(R.id.tvQuotes);
        tvAuthor = itemView.findViewById(R.id.tvAuthor);
        btnCopy = itemView.findViewById(R.id.btnCopy);
        btnShare = itemView.findViewById(R.id.btnShare);
        relLay = itemView.findViewById(R.id.relLay);

    }


} // MyViewHolder Class Ends Here -------------------------------
