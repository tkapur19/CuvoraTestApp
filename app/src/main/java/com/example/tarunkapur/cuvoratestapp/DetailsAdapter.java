package com.example.tarunkapur.cuvoratestapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

// Adapter class for detail RecycerView

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.viewHolder> {

    private List<info> listItems=new ArrayList<info>();
    private Context context;


    public DetailsAdapter(List<info> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public DetailsAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_card_view,parent,false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailsAdapter.viewHolder holder, int position) {

        info listItem=listItems.get(position);


        holder.key.setText(listItem.getKey());
        holder.value.setText(listItem.getValue());





        Log.i("myMessage", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView key;
        private TextView value;


        public viewHolder(View itemView) {
            super(itemView);

            key=(TextView) itemView.findViewById(R.id.key);
            value=(TextView) itemView.findViewById(R.id.value);


        }


    }


}
