package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ExperienceListAdapter extends BaseAdapter {
    ArrayList<String> experiences = new ArrayList<>();

    public  void addExperience(String exp){
        experiences.add(exp);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return experiences.size();
    }

    @Override
    public Object getItem(int i) {
        return experiences.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.id.experience_layout_id,viewGroup,false);
    }
}
