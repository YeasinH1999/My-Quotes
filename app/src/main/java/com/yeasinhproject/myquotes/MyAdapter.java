package com.yeasinhproject.myquotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List <MyQuotes> myQuotesList;
    private Context context;

    // ----------------------- MyAdapter Constructor -----------------------------------

    public MyAdapter(List<MyQuotes> myQuotesList, Context context) {
        this.myQuotesList = myQuotesList;
        this.context = context;
    }

    // ----------------------- MyAdapter Constructor Ends Here ---------------------------

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(myView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final MyQuotes myQuotesPosition = myQuotesList.get(position);

        holder.tvQuotes.setText(myQuotesPosition.getTvQuotes());
        holder.tvAuthor.setText(myQuotesPosition.getTvAuthor());

        SetOnClickListener(holder, context);

        LoadAnimation(holder, context);

    }

    @Override
    public int getItemCount() {

        return myQuotesList.size();
    }

    // ---------------------------------- Private Method -----------------------------------------\\

    private void SetOnClickListener (MyViewHolder holder, Context context) {

        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copied Text", "Quote : "+holder.tvQuotes.getText().toString()+"\n\nAuthor : "+holder.tvAuthor.getText().toString());
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(context.getApplicationContext(), "Copied!", Toast.LENGTH_SHORT).show();

            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Quote : "+holder.tvQuotes.getText().toString()+"\n\nAuthor : "+holder.tvAuthor.getText().toString());
                Intent chooserIntent = Intent.createChooser(shareIntent, "Open With");
                chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(chooserIntent);
            }
        });

    }


    private void LoadAnimation (MyViewHolder holder, Context context) {

        holder.tvQuotes.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_two));
        holder.tvAuthor.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_two));
        holder.relLay.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));
    }

    // ---------------------------------- Private Method -----------------------------------------\\




} // MyAdapter Class Ends Here ------------------------------------------
