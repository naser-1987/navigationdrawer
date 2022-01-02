package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    public static final String F_NAME="f_name";
    public static final String L_NAME="l_name";
    public static final String OLD="old";
    public static final String JOB_TITLE="job_title";
    public static final String IMAGE_ID="image_id";
    private String f_name,l_name,job_title, image_id;
    private int old;

    public FirstFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String Pfname, String Plname,String Pjob_title,String Pimage_id, int Pold) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(F_NAME,Pfname);
        args.putString(L_NAME,Plname);
        args.putString(JOB_TITLE,Pjob_title);
        args.putInt(OLD,Pold);
        args.putString(IMAGE_ID,Pimage_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            f_name=getArguments().getString(F_NAME,"");
            l_name=getArguments().getString(L_NAME,"");
            job_title=getArguments().getString(JOB_TITLE,"");
            old=getArguments().getInt(OLD,0);
            image_id=getArguments().getString(IMAGE_ID,"");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        inflateView(view);
    }
    public void inflateView(View view){
        TextView fname_tv , lname_tv , old_tv, job_tv;
        ImageView image_iv;
        fname_tv  = view.findViewById(R.id.home_fragment_tv_fname);
        lname_tv  = view.findViewById(R.id.home_fragment_tv_lname);
        old_tv = view.findViewById(R.id.home_fragment_tv_old);
        job_tv  = view.findViewById(R.id.home_fragment_tv_jobtitle);
        image_iv  = view.findViewById(R.id.home_fragment_iv);
        if(Uri.parse(image_id) != null)
            image_iv.setImageURI(Uri.parse(image_id));

        else
            image_iv .setImageResource(R.drawable.ic_photo);

        fname_tv.setText(f_name);
        lname_tv.setText(l_name);
        old_tv.setText(String.valueOf(old));
        job_tv.setText(job_title);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        /*    arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getData() != null){
                    image = (Bitmap) result.getData().getExtras().get("data");

                }else{
                    image = null;
                }
            }
        });
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        arl.launch(intent);*/
    }
}