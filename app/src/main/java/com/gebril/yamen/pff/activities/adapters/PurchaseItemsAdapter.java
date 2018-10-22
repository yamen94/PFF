package com.gebril.yamen.pff.activities.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.PurchaseItems;

import java.util.ArrayList;


public class PurchaseItemsAdapter extends RecyclerView.Adapter<PurchaseItemsAdapter.ViewHolder> {

    private ArrayList<PurchaseItems> purchaseItems;

    public PurchaseItemsAdapter(ArrayList<PurchaseItems> PurchaseItems) {
        this.purchaseItems = PurchaseItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_item,parent,false);
        Log.i("TEST","inn");
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getImageView().setImageResource(purchaseItems.get(position).getImage());
        holder.getPrice().setText(Integer.toString(purchaseItems.get(position).getPrice()));
        holder.getTitle().setText(purchaseItems.get(position).getTittle());
    }

    @Override
    public int getItemCount() {
        return purchaseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         private TextView title, price;
         private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_main);
            title = itemView.findViewById(R.id.tv_title);
            price = itemView.findViewById(R.id.tv_price);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }
    }
}
