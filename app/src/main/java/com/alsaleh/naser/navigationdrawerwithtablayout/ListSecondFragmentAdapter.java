package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListSecondFragmentAdapter extends RecyclerView.Adapter<ListSecondFragmentAdapter.VH> {
  ArrayList<String> experiences;

    public ListSecondFragmentAdapter(ArrayList<String> experiences) {
        this.experiences = experiences;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.experience,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
         holder.exper.setText(experiences.get(position));
    }

    @Override
    public int getItemCount() {
        return experiences.size();
    }

    class VH extends RecyclerView.ViewHolder{
       TextView exper;
        public VH(@NonNull View itemView) {
            super(itemView);
            exper = itemView.findViewById(R.id.experience_layout_id);
        }
    }

}
