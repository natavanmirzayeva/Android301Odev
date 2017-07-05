package com.tatlicilar.sosyalmedyauygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private EditText passwordEditText;
    private TextView textView;
    Intent intent;
    DbAdapter dbAdapter = new DbAdapter(this);
    ArrayList<String> liste = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        passwordEditText = (EditText) findViewById(R.id.password);
        textView = (TextView) findViewById(R.id.passwordHint);
        textView.setVisibility(View.GONE);

        /* Set Text Watcher listener */
        passwordEditText.addTextChangedListener(passwordWatcher);

        intent = getIntent();
    }

    private final TextWatcher passwordWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           // dbAdapter.open();
           // liste = (ArrayList<String>) dbAdapter.getAllContacts();
          //  dbAdapter.close();

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textView.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText("Girdigin isim: " + passwordEditText.getText());
            }
        }
    };

}

             /*   if(liste.size() == 0)
                {
                    textView.setText("Girdigin isimmm: " + passwordEditText.getText());
                }
                else
                {
                    for(int i=0;i<liste.size();i++)
                    {
                        if(passwordEditText.toString() == liste.get(i))
                        {
                            textView.setText("Girdigin isim: " + passwordEditText.getText());
                        }*/
