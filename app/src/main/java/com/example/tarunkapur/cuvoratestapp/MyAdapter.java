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
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tarunkapur on 27/12/17.
 */

// Adapter Class for Main RecyclerView of MainActivity

class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    private List<ModalClass> listItems;
    private Context context;


    public MyAdapter(List<ModalClass> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.viewHolder holder, int position) {


        ModalClass listItem=listItems.get(position);


        holder.celebName.setText(listItem.getOwnerName());
        Picasso.with(context).load(listItem.getImageURL()).into( holder.carImage);





        Log.i("myMessage", "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView celebName;
        private CircleImageView carImage;


        public viewHolder(View itemView) {
            super(itemView);

            celebName=(TextView) itemView.findViewById(R.id.celeb_name);
            carImage=(CircleImageView) itemView.findViewById(R.id.car_image);
            itemView.setOnClickListener(this);


        }

        // Handling OnClick on the View of every element in the RecyclerView
        @Override
        public void onClick(View v) {
            // Obtaining the position of view from where the click has been invoked.
            int position=getAdapterPosition();

            // Initialising intent to send data to the next detail activity.
            Intent intent=new Intent(context,DetailScreenActivity.class);

            // Extracting list<info> from the modalClass object on which the click has been invoked by the user
            List<info> infoList=listItems.get(position).getInfoList();

            // Initialising bundle for sending list<info> to detail activity.
            Bundle args = new Bundle();
           args.putSerializable("ARRAYLIST",(Serializable)infoList);
            intent.putExtra("BUNDLE", args );
            // Sending imageUrl and shareText to detail activity with intent.
           intent.putExtra("ImageUrl",listItems.get(position).getImageURL());
           intent.putExtra("shareText",listItems.get(position).getShareText());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}


