package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class SecondFragment extends Fragment   {
    private static int list_size;

    ArrayList<String> experiences = new ArrayList<>();
    ListSecondFragmentAdapter secondFragmentAdapter;
    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(ArrayList<String> Pexperiences) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        if(Pexperiences != null) {
            list_size = Pexperiences.size();
            for (int i = 0; i < list_size; i++) {
                args.putString(i + "", Pexperiences.get(i));
            }
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            for (int i = 0 ; i <list_size;i++) {
                experiences.add(getArguments().getString(i+""));
            }
         getArguments().clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button add = view.findViewById(R.id.experiences_fragment_btn);
        RecyclerView experiences_lv = view.findViewById(R.id.experiences_fragment_lv);

        secondFragmentAdapter = new ListSecondFragmentAdapter(experiences);
        experiences_lv.setAdapter(secondFragmentAdapter);
        experiences_lv.setLayoutManager(new LinearLayoutManager(getActivity()));

        AddExperiencefragment add_exper = new AddExperiencefragment();
        add.setOnClickListener(view1 -> add_exper.show(getParentFragmentManager(),null));

    }

}