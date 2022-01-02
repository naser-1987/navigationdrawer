package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {


    private contactActivityListener listener;
    static  int infos_length;
    private String name,email,telefon;
    private int btn_type;
    private EditText name_et, email_et, telefon_et;
    private Button save_btn,edit_btn;

    public ThirdFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String[] infos, int x) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt("btn_type",x);
         if(infos!=null){
             infos_length = infos.length;
             for (int i =0;i<3;i++)
                 args.putString(i+"",infos[i]);
         }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                name = getArguments().getString(0+"");
                email =getArguments().getString(1+"");
                telefon =getArguments().getString(2+"");
                btn_type = getArguments().getInt("btn_type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        save_btn = view.findViewById(R.id.third_fragment_contact_btn);
        edit_btn = view.findViewById(R.id.third_fragment_contact_btn_edit);
        name_et= view.findViewById(R.id.third_fragment_contact_name_et_label);
        email_et = view.findViewById(R.id.third_fragment_contact_email_et_label);
        telefon_et = view.findViewById(R.id.third_fragment_contact_telefon_et_label);
        if(name != null && btn_type ==1){
            fillField();
           setdisabledField();
        } else{
            fillField();
            setEnabledField();
        }
        save_btn.setOnClickListener(view1 -> listener.onClickContactInfos(new String[]{name_et.getText().toString(),email_et.getText().toString(),telefon_et.getText().toString()},1));
        edit_btn.setOnClickListener(view12 -> listener.onClickContactInfosEdit(new String[]{name_et.getText().toString(),email_et.getText().toString(),telefon_et.getText().toString()},2));
    }
    public interface contactActivityListener{
        void onClickContactInfos(String[] infos,int x);
        void onClickContactInfosEdit(String[] infos,int x);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof contactActivityListener)
            listener= (contactActivityListener)context;
        else
            throw new ClassCastException("the activity must implement interface contactActivityListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
    public void fillField(){
        name_et.setText(name);

        email_et.setText(email);

        telefon_et.setText(telefon);

        save_btn.setEnabled(false);
    }
    public void setdisabledField(){
        name_et.setEnabled(false);
        email_et.setEnabled(false);
        telefon_et.setEnabled(false);
        save_btn.setEnabled(false);
        edit_btn.setEnabled(true);
    }
    public void setEnabledField(){
        name_et.setEnabled(true);
        email_et.setEnabled(true);
        telefon_et.setEnabled(true);
        edit_btn.setEnabled(false);
        save_btn.setEnabled(true);
    }
}