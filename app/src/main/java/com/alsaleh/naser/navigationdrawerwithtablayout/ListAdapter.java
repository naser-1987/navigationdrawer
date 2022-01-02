package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.VH>{

    protected String [] names;
    protected onClickOnRecyclerView listener;
    public ListAdapter(String[] names, onClickOnRecyclerView Plistener) {
        this.names = names;
        this.listener = Plistener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.optionlayout,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBind(names[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class VH extends RecyclerView.ViewHolder {
        String name;
        TextView name_tv;
        public VH(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.option_layout_view_tv);
            itemView.setOnClickListener(view -> listener.onClickOnOption(name));
        }
        public  void onBind(String Pname){
            name = Pname;
            name_tv.setText(Pname);
        }
    }
    public interface onClickOnRecyclerView{
        void onClickOnOption(String name);
    }
}
