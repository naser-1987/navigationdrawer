package com.alsaleh.naser.navigationdrawerwithtablayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    public final static String FNAME = "fname";
    public final static String LNAME = "lname";
    public final static String JOB_TITLE = "job title";
    public final static String AGE = "age";
    public final static String IMAGE_URI = "image uri";
    ActivityResultLauncher<Intent> arl_takePhoto;

    EditText fname, lname, age, job;
    Button take_photo_btn,submit_btn;

    String final_image_path;
    Bitmap image;
    Intent info;
    String path_pic;
    File path;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        arl_takePhoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if( ( result.getData() != null ) &&  ( result.getResultCode() == RESULT_OK )) {
                image = (Bitmap) result.getData().getExtras().get("data");
                // Bitmap bitmap= BitmapFactory.decodeStream(image);
                Thread save = new Thread(() -> {
                    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    path_pic= "/img"+String.format("%d",System.currentTimeMillis());

                    File img = new File(path, path_pic+".jpeg");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(img);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    //FileOutputStream fos = MainActivity.this.openFileOutput(,Context.MODE_PRIVATE);
                   image.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                    try {
                        final_image_path= path+path_pic+".jpeg";
                        /*
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                path_tv.setText(final_image_path);
                            }
                        });
                        */
                        assert fos != null;
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                save.start();
            }

        });


        // inflate the views //
        take_photo_btn = findViewById(R.id.info_layout_load_btn);
        submit_btn = findViewById(R.id.info_layout_submit_btn);
        fname = findViewById(R.id.info_layout_fname_et);
        lname = findViewById(R.id.info_layout_lname_et);
        job = findViewById(R.id.info_layout_job_et);
        age = findViewById(R.id.info_layout_age_et);

        handler = new Handler(this.getMainLooper());

        take_photo_btn.setOnClickListener(view -> {
            Intent take_photo = new Intent();
            take_photo.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            arl_takePhoto.launch(take_photo);
        });
        submit_btn.setOnClickListener(view -> {
            info= new Intent(MainActivity2.this,MainActivity.class);
            String f_name =fname.getText().toString();
            String l_name =lname.getText().toString();
            String job_title =job.getText().toString();
            String age_ =age.getText().toString();
            if(f_name.equalsIgnoreCase(""))
            {
                fname.setHint("please you must enter your first name");

            }else if (l_name.equalsIgnoreCase("")){
                lname.setHint("please you must enter your last name");

            }else if(job_title.equalsIgnoreCase("")){
                job.setHint("please you must enter your job title ");

            }else if(age_.equalsIgnoreCase("")){
                age.setHint("please you must enter your age");

            }else{

                info.putExtra(FNAME,f_name);
                info.putExtra(LNAME,l_name);
                info.putExtra(JOB_TITLE,job_title);
                info.putExtra(AGE,age_);
                info.putExtra(IMAGE_URI,final_image_path);
                startActivity(info);
            }

        });

    }
}