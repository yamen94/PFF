package com.gebril.yamen.pff.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gebril.yamen.pff.R;

import java.util.ArrayList;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    ArrayList<Integer> images;

    public OffersAdapter(ArrayList images) {
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.purchasing_offer_item,parent,false);
        Log.i("TEST","inn");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getImageView().setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.img_id);
             imageView.setOnClickListener(this);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        public void onClick(View view) {
            if (view.getId() == imageView.getId())
            {
                Log.i("TEST", "the position is "+getAdapterPosition());
            }else{
                Log.i("TEST", "the position wrong is "+getAdapterPosition());
            }

        }
    }
}
