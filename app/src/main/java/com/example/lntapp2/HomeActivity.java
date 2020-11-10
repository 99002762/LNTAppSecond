package com.example.lntapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.w(TAG,"onCreate");

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        String data = extras.getString("srisai");

        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        String data2 = "hello everyone";
        textView.setText(data);
        textView2.setText(data2);
    }

}