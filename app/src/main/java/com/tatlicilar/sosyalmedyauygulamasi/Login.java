package com.tatlicilar.sosyalmedyauygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText name,pass;
    ArrayList<Kisi> uyeler;
    String loginIsim, loginPass;
    Intent intent;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        name= (EditText) findViewById(R.id.loginName);
        pass= (EditText) findViewById(R.id.password);


        kisileriOlustur();

    }

    public void onClick (View v)
    {
        switch (v.getId()){
            case R.id.girisBtn:
                loginIsim = name.getText().toString();
                loginPass = pass.getText().toString();
                int yetkili=0;
                for(int i=0;i<uyeler.size();i++) {
                    if (uyeler.get(i).getIsim().equals(loginIsim) && uyeler.get(i).getParola().equals(loginPass)) {
                        yetkili = 1;
                        intent = new Intent(this, HomePage.class);
                        intent.putExtra("kisi", uyeler.get(i));
                        startActivity(intent);
                    }
                }
                if(yetkili==0)
                    Toast.makeText(this, "Hatalı giriş yaptınız", Toast.LENGTH_SHORT).show();
                break;
            case R.id.uyeBtn:
                intent = new Intent(this, Register.class);
                startActivity(intent);
                break;


        }

    }

    public void kisileriOlustur(){

        Kisi k1 = new Kisi("Rümeysa","1234","rumeysa@gmail.com","Kadın","07.01.1998",R.drawable.misty,"http://www.google.com");
        Kisi k2 = new Kisi("Cici","5678","cici@gmail.com","Kadın","08.01.1998",R.drawable.sailormoon,"http://www.facebook.com");
        Kisi k3 = new Kisi("Bici","9101","bici@gmail.com","Erkek","09.01.1998",R.drawable.mask,"http://www.twitter.com");
        Kisi k4 = new Kisi("Neval","1112","neval@gmail.com","Kadın","10.01.1998",R.drawable.angel,"https://github.com/Surinovi");

        k1.arkadasEkle(k3);
        k1.arkadasEkle(k4);
        k2.arkadasEkle(k1);
        k2.arkadasEkle(k4);
        k3.arkadasEkle(k1);
        k3.arkadasEkle(k2);
        k4.arkadasEkle(k1);
        k4.arkadasEkle(k2);

        uyeler=new ArrayList<Kisi>();

        uyeler.add(k1);
        uyeler.add(k2);
        uyeler.add(k3);
        uyeler.add(k4);




    }

}
