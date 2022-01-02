package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {

    private static final String IMAGE_ID= "image_id";
    private int image_id;

    public ImageFragment() {
        // Required empty public constructor
    }


    public static ImageFragment newInstance(int Pimage_id) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(IMAGE_ID,Pimage_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image_id = getArguments().getInt(IMAGE_ID,0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView image = view.findViewById(R.id.imagefragment_im);
        image.setImageResource(image_id);
    }
}