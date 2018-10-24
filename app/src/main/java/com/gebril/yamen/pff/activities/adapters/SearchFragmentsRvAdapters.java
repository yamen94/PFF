package com.gebril.yamen.pff.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.Search;

import java.util.ArrayList;


public class SearchFragmentsRvAdapters extends RecyclerView.Adapter<SearchFragmentsRvAdapters.ViewHolder> {
   private ArrayList<Search> list;

    public SearchFragmentsRvAdapters(ArrayList<Search> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_items , parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getHeader_1().setText(list.get(position).getHeader1());
        holder.getHeader_2().setText(list.get(position).getHeader1());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
      private   TextView header_1, header_2;



        TextView getHeader_1() {
            return header_1;
        }


        ViewHolder(View itemView) {
            super(itemView);
            header_1 = itemView.findViewById(R.id.header_1);
            header_2 = itemView.findViewById(R.id.header_2);
        }

        TextView getHeader_2() {
            return header_2;
        }
    }
}
