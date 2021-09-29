package com.example.smartrestaurentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class QRGenerator extends AppCompatActivity {

    //Initailize variable
    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;
    Button btSave;
    OutputStream outstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_generator);

        //Assign Variables
        etInput=findViewById(R.id.et_input);
        btGenerate=findViewById(R.id.bt_generate);
        ivOutput=findViewById(R.id.iv_output);
        btSave=findViewById(R.id.bt_Save);


        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get input value from edit text
                String sText=etInput.getText().toString().trim();

                //Initialize multi-format writer
                MultiFormatWriter writer=new MultiFormatWriter();

                try {
                    //Initialize Bit Matrix
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);

                    //Initialize Barcode encoder
                    BarcodeEncoder encoder=new BarcodeEncoder();

                    //Initialize bitmap
                    Bitmap bitmap=encoder.createBitmap(matrix);

                    //Set bitmap on ImageView
                    ivOutput.setImageBitmap(bitmap);

                    //Initialize input manager
                    InputMethodManager manager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    //hide soft keyboard
                    manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),0);
                    btSave.setVisibility(View.VISIBLE);
                }
                catch (WriterException exception)
                {
                    exception.printStackTrace();
                }
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable=(BitmapDrawable) ivOutput.getDrawable();
                Bitmap bitmap=drawable.getBitmap();

                File filepath= Environment.getExternalStorageDirectory();
                File dir=new File(filepath.getAbsolutePath() + "/QR_Codes/");
                dir.mkdir();
                File file=new File(dir,System.currentTimeMillis() + ".jpg");
                try
                {
                    outstream =new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outstream);
                Toast.makeText(getApplicationContext(),"Image Save to "+dir.toString()+" !!",Toast.LENGTH_SHORT).show();

            }
        });
        
    }
}