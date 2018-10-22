package com.gebril.yamen.pff.activities.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Utils.LeaderboardListGenerator;
import com.gebril.yamen.pff.activities.tools.FilterDescription;

import java.io.IOException;
import java.util.ArrayList;


public class LeaderboardFilterAdapter extends RecyclerView.Adapter<LeaderboardFilterAdapter.filterViewHolder> {

        private FilterDescription filterDescription;
        private ArrayList<String> filterDataList;
        private int rvItemWidth = 0;
        private Context context;
        private final String TAG = "TEST";
        private int rvType;
        private LeaderboardFilterAdapter filterAdapter;
        private final int CATEGORY = 1;
        private final int COUNTRY = 2;
        private RecyclerView recyclerView;
        private LeaderboardListGenerator listGenerator ;
        private TextView descriptionTV;

        private int selectedItem = -1;

        public LeaderboardFilterAdapter(ArrayList<String> filterData, Context context, int rvType, TextView tv, float rvItemWidth) {
            this.filterDataList = filterData;
            this.rvItemWidth = (int) rvItemWidth;
            this.context = context;
            this.rvType = rvType;
            this.filterDescription = FilterDescription.getInstance(context);
            this.descriptionTV = tv;
            listGenerator = new LeaderboardListGenerator(context);
        }

        public LeaderboardFilterAdapter(ArrayList<String> filterData, Context context, int rvType, TextView tv, LeaderboardFilterAdapter filterAdapter, float rvItemWidth, RecyclerView recyclerView) {
            this.filterDataList = filterData;
            this.rvItemWidth = (int) rvItemWidth;
            this.context = context;
            this.rvType = rvType;
            this.filterAdapter = filterAdapter;
            this.filterDescription = FilterDescription.getInstance(context);
            this.descriptionTV = tv;
            this.recyclerView = recyclerView;
            listGenerator = new LeaderboardListGenerator(context);
        }


        @Override
        public filterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_filter_rv_item,
                    parent, false);

            Log.i(TAG, "oncreate itemwidth " + rvItemWidth);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.width = rvItemWidth;
            view.setLayoutParams(layoutParams);

            return new filterViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(filterViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            String filterLabel = filterDataList.get(position);


            //TODO: change the setOnclickListener to the ViewHolder

            holder.tvFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //    Log.i("TEST", "inside on click with position " + position + " rvType " + rvType);
                    if (position != 0 || rvType != COUNTRY) {
                        if (rvType == CATEGORY) {
                            setSelecteditem(position);
                            try {
                                filterAdapter.filterDataList.clear();
                                filterAdapter.filterDataList.addAll(listGenerator.generateFilterList(position));
                                recyclerView.smoothScrollToPosition(0);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            filterAdapter.setSelecteditem(0);
                        } else {
                            setSelecteditem(position);
                        }
                    }
                }
            });

            holder.tvFilter.setText(filterLabel);
            if (position == selectedItem) {
                Log.d(TAG, "center" + position);
                holder.tvFilter.setTextColor(context.getResources().getColor(R.color.SelectedItem));
                holder.tvFilter.setTextSize(18);
                filterDescription.updateDescription(rvType,position);
                Log.i("TEST","desc "+descriptionTV.getText());
                String text = filterDescription.getFilterDescriptionTitle();
                descriptionTV.setText(text);
                Log.i("TEST","desc after  "+descriptionTV.getText());
            } else {
                holder.tvFilter.setTextColor(context.getResources().getColor(R.color.unSelectedItem));
                holder.tvFilter.setTextSize(16);
            }
        }

        public void setSelecteditem(int selecteditem) {
            this.selectedItem = selecteditem;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return filterDataList.size();
        }


        public class filterViewHolder extends RecyclerView.ViewHolder {
            TextView tvFilter;

            filterViewHolder(View itemView) {
                super(itemView);
                tvFilter = itemView.findViewById(R.id.txt_date);
            }


        }
    }

