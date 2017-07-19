package com.papcon.papcon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static com.papcon.papcon.LoginActivity.pref;
import static com.papcon.papcon.LoginActivity.editor;

public class InformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("_내정보");

        TextView idText = (TextView) findViewById(R.id.idText);
        idText.setText(pref.getString("id",""));

    }
}
