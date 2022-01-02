package com.alsaleh.naser.navigationdrawerwithtablayout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddExperiencefragment extends DialogFragment {

    DialogFragmentNewExper listener;

    public AddExperiencefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return  inflater.inflate(R.layout.fragment_add_experiencefragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button ok_btn,cancel_btn;
        EditText newexperience_et;
        ok_btn = view.findViewById(R.id.add_experience_layout_dialog_btn_ok);
        cancel_btn = view.findViewById(R.id.add_experience_layout_dialog_btn_cancel);
        newexperience_et = view.findViewById(R.id.add_experience_layout_dialog_et);
        ok_btn.setOnClickListener(view1 -> {
            String experience = newexperience_et.getText().toString();
            if(!experience.equalsIgnoreCase("")) {
                listener.onDialogFragmentNewExper(experience);
            }
        dismiss();
        });
        cancel_btn.setOnClickListener(view12 -> dismiss());
    }
    public interface DialogFragmentNewExper{
        void onDialogFragmentNewExper(String exper);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof DialogFragmentNewExper){
            listener = (DialogFragmentNewExper) context;
        }else{
            throw new ClassCastException("the activity must implement the interface DialogFragmentNewExper");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}