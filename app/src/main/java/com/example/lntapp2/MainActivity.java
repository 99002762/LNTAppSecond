package com.example.lntapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String MYPREFS = "myprefs";
    private static final String NAMEKEY = "namekey";
    private static final String PWDKEY = "pwdkey";
    EditText nameEditText,pwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        nameEditText =  findViewById(R.id.editTextName);
        pwdEditText = findViewById(R.id.editTextPwd);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
        saveData();
    }

    private void saveData() {
        Log.i(TAG,"saveData");

        //get the data from the edittext
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        //create a file names myprefs
        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //open the file
        SharedPreferences.Editor editor = preferences.edit();
        //write to the file
        editor.putString(NAMEKEY,name);
        editor.putString(PWDKEY,pwd);
        //save the file
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
        restoreData();
    }

    private void restoreData() {
        Log.i(TAG,"restoreData");

        //open the file
        SharedPreferences preferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //read the file
        String name = preferences.getString(NAMEKEY,"");
        String pwd = preferences.getString(PWDKEY,"");
        //set the data in edittexts
        nameEditText.setText(name);
        pwdEditText.setText(pwd);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    public void clickHandler(View view) {
        Log.e(TAG,"clickHandler");
        switch (view.getId()){
            case R.id.buttonlogin:
                startHome();
                break;
            case R.id.buttonsignup:
                Intent dialIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
                        //ACTION_DIAL, Uri.parse("tel:1234567"));
                startActivity(dialIntent);
                break;
        }


    }

    private void startHome() {

        Intent hIntent = new Intent( MainActivity.this,HomeActivity.class);
        hIntent.putExtra("srisai","chintu");
        startActivity(hIntent);

    }

    public void clickHandler2(View view) {
    }
}