package com.tatlicilar.sosyalmedyauygulamasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;

public class HomePage extends AppCompatActivity {

    Intent intent,intentarama,intentprofil,intent2;
    ImageView img;
    TextView name,tarih;
    ListView listView;
    Kisi k;
    MyAdapter adapter;
    int sira;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);




        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        img = (ImageView) findViewById(R.id.profilImg);
        name = (TextView) findViewById(R.id.nameTvHome);
        tarih= (TextView) findViewById(R.id.tarihTvHome);
        listView = (ListView) findViewById(R.id.arkadasList);


        intent = getIntent();
        k = (Kisi) intent.getSerializableExtra("kisi");

        img.setImageResource(k.getResim());
        name.setText(k.getIsim());
        tarih.setText(k.getTarih());

        adapter=new MyAdapter(this, k.getArkadaslarim());
        listView.setAdapter(adapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder alert = new AlertDialog.Builder(HomePage.this);
                alert.setTitle("Arkadaş Sil");
                alert.setMessage(k.getArkadaslarim().get(position).getIsim()+"'i silmek istediğinden emin misin?");

                sira = position;
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        k.arkadasSil(sira);
                        adapter.notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();

                return false;
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar öğelerindeki basılmaları idare edelim
        switch (item.getItemId()) {
            case R.id.action_arama:
                intentarama = new Intent(this,Search.class);
                startActivity(intentarama);
                return true;
            case R.id.action_profil:
                intentprofil = new Intent(this,EditProfil.class);
                startActivity(intentprofil);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void profilGoster(View v)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Github Profili");

        WebView wv = new WebView(this);
        wv.loadUrl(k.getUrl());
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(HomePage.this);
        alert.setTitle("Çıkış");
        alert.setMessage("Çıkmak istediğinden emin misin?");

        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent2 = new Intent(HomePage.this,Login.class);
                startActivity(intent2);
            }
        });
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
