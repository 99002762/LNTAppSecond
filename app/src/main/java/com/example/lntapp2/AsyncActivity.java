package com.example.lntapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncActivity extends AppCompatActivity {
    private static final String TAG = AsyncActivity.class.getSimpleName();
    ProgressBar progressBar;
    EditText mBookInput;
    TextView mTitleText,mAuthorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        progressBar = findViewById(R.id.progressBar);
        mBookInput = findViewById(R.id.bookInput);
        //String queryString = mBookInput.getText().toString();
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);

    }

    public void handleClick(View view) {
        Log.i(TAG,"handleClick");
//        //download something from internet
//        DownloadTask downloadTask = new DownloadTask(progressBar);
//        downloadTask.execute("https://urlForimagetobedownloaded");
//        //show the download progress on the progress bar
        String queryString = mBookInput.getText().toString();

        new FetchBook(mTitleText, mAuthorText).execute(queryString);


    }
}