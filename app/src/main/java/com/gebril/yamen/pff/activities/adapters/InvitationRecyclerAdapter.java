package com.gebril.yamen.pff.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.Invitation;

import java.util.ArrayList;


public class InvitationRecyclerAdapter extends RecyclerView.Adapter<InvitationRecyclerAdapter.InvitationViewHolder> {

    private ArrayList<Invitation> list;


    public InvitationRecyclerAdapter(ArrayList<Invitation> list) {
        this.list = list;
    }

    @Override
    public InvitationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_invitation_recycle_item, parent, false);
        return new InvitationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InvitationViewHolder holder, int position) {
        holder.body.setText(list.get(position).getInvitation_body());
        holder.creation_date.setText(list.get(position).getCreation_date());
        holder.logo.setImageResource(list.get(position).getInvitaiton_image());
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }


    class InvitationViewHolder extends RecyclerView.ViewHolder {

        TextView body;
        TextView creation_date;
        ImageView logo;
        ImageView accept;
        ImageView decline;
        ImageView full_details;
        InvitationViewHolder(View v) {
            super(v);
            body = v.findViewById(R.id.inv_body);
            creation_date =  v.findViewById(R.id.inv_date);
            logo =  v.findViewById(R.id.inv_image);
            accept =  v.findViewById(R.id.inv_accept);
            decline =  v.findViewById(R.id.inv_decline);
            full_details =  v.findViewById(R.id.inv_full_details);

            /*
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

            */

        }
    }


}