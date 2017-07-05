package com.tatlicilar.sosyalmedyauygulamasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Register extends AppCompatActivity {


    EditText isim,parola;
    Button kaydet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        try {
            String destPath = "/data/data/" + getPackageName() + "/databases/LoginDb";
            File file = new File(destPath);
            File path = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                Log.d("girdi","girdi");
                path.mkdirs();
                CopyDB( getBaseContext().getAssets().open("LoginDb"),
                        new FileOutputStream(destPath));
            }
            Log.d("cikti","cikti");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        kaydet =(Button)findViewById(R.id.btn13);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAdapter dbadapter = new DbAdapter(v.getContext());
                isim= (EditText) findViewById(R.id.name);
                parola= (EditText) findViewById(R.id.surname);
                // Iletisim ekle
                dbadapter.open();

                dbadapter.insertContact(isim.toString(),parola.toString());
               // dbadapter.deleteContact(2);

            //    isim.setText(String.valueOf(dbadapter.getContact(2).getColumnCount()));
                if((dbadapter.insertContact(isim.toString(),parola.toString())) != 0)
                {
                   Log.d("eklendi","eklendi");
                }
                else
                {
                    Log.d("herzamanki gibi ","olmadi");
                }

                dbadapter.close();
            }
        });











    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
}
