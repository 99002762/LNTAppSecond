package com.example.lntapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.lntapp2.database.DbAccessObj;
import com.example.lntapp2.database.FeedReaderContract;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String MYPREFS = "myprefs";
    private static final String NAMEKEY = "namekey";
    private static final String PWDKEY = "pwdkey";
    DbAccessObj dbAccessObj;
    EditText nameEditText,pwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        nameEditText =  findViewById(R.id.editTextName);
        pwdEditText = findViewById(R.id.editTextPwd);

        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
        ListView dbListView = findViewById(R.id.dblistview);
//        Uri uriSms = Uri.parse("content://sms/inbox");
//        Cursor dataCursor =  getContentResolver().query(uriSms,null,null,null,null);
//        Uri allContacts = Uri.parse("content://contacts/people");
//        Cursor dataCursor =  getContentResolver().query(allContacts,null,null,null,null);
        Uri callOgUri = CallLog.Calls.CONTENT_URI;
        Cursor dataCursor =  getContentResolver().query(callOgUri,null,null,null,null);
        //Cursor dataCursor = dbAccessObj.getRows();
        //put the data into adapter
        CursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.row_listview,
                dataCursor,
                new String[]{CallLog.Calls.NUMBER,CallLog.Calls.CACHED_NAME},
//                 new String[] {
//                ContactsContract.Contacts.DISPLAY_NAME,
//                ContactsContract.Contacts._ID},
               // new String[]{"body","address"},
                //new String[]{FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE},
                //"title","subtitle"},
                new int[] {R.id.textviewRow,R.id.textViewsubtitle});
        //set the adapter onto the listview
        dbListView.setAdapter(adapter);

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
                //startHome();
                getCredentials();
                break;
            case R.id.buttonsignup:
                Intent dialIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
                        //ACTION_DIAL, Uri.parse("tel:1234567"));
                startActivity(dialIntent);
                break;
        }


    }

    private void getCredentials() {
     String pwd = dbAccessObj.query(nameEditText.getText().toString());
       pwdEditText.setText(pwd);
        dbAccessObj.query(nameEditText.getText().toString());
    }

    private void startHome() {

        Intent hIntent = new Intent( MainActivity.this,HomeActivity.class);
        hIntent.putExtra("srisai","chintu");
        startActivity(hIntent);

    }

//    public void clickHandler2(View view) {
//    }

    public void handleDbPart(View view) {
        switch (view.getId()){
            case R.id.buttonPut:
                String title = nameEditText.getText().toString();
                String subtitle = pwdEditText.getText().toString();

                dbAccessObj.createRow(title,subtitle);
                break;
            case R.id.buttonGet:
                String data =  dbAccessObj.readRow();
                //set the data onto textview
                TextView dbTextView = findViewById(R.id.textViewdb);
                dbTextView.setText(data);
                break;

        }
    }
}