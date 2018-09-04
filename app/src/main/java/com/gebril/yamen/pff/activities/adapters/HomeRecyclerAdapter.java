package com.gebril.yamen.pff.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.Match;
import java.util.ArrayList;


public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MatchViewHolder> {

    private ArrayList<Match> list;


    public HomeRecyclerAdapter(ArrayList<Match> list) {
        this.list = list;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_recycle_items, parent, false);
        HomeRecyclerAdapter.MatchViewHolder holder = new HomeRecyclerAdapter.MatchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.firstTeamNameTextView.setText(list.get(position).getFirst_team_name());
        holder.firstTeamImageView.setImageResource(list.get(position).getFirst_team_image());
        holder.firstTeamScoreTextView.setText(Integer.toString(list.get(position).getFirst_team_score()));
        holder.secondTeamNameTextView.setText(list.get(position).getSecond_team_name());
        holder.secondTeamImageView.setImageResource(list.get(position).getSecond_team_image());
        holder.secondTeamScoreTextView.setText(Integer.toString(list.get(position).getSecond_team_score()));
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }


    class MatchViewHolder extends RecyclerView.ViewHolder {

        public TextView firstTeamNameTextView;
        public TextView secondTeamNameTextView;
        public TextView firstTeamScoreTextView;
        public TextView secondTeamScoreTextView;
        public TextView matchTypeTextView;
        public ImageView firstTeamImageView;
        public ImageView secondTeamImageView;
        public LinearLayout fullDetailsLayout;

        public MatchViewHolder(View v) {
            super(v);
            firstTeamImageView = (ImageView) v.findViewById(R.id.first_team_image);
            secondTeamImageView = (ImageView) v.findViewById(R.id.second_team_image);
            firstTeamScoreTextView = (TextView) v.findViewById(R.id.first_team_score);
            secondTeamScoreTextView = (TextView) v.findViewById(R.id.second_team_score);
            firstTeamNameTextView = (TextView) v.findViewById(R.id.first_team_name);
            secondTeamNameTextView = (TextView) v.findViewById(R.id.second_team_name);
            fullDetailsLayout = (LinearLayout) v.findViewById(R.id.full_details_layout);
            matchTypeTextView = (TextView) v.findViewById(R.id.match_type);

            fullDetailsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "go to full details", Toast.LENGTH_SHORT).show();
                }
            });

            firstTeamImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "go to first team details", Toast.LENGTH_SHORT).show();
                }
            });

            secondTeamImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "go to second team details", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}