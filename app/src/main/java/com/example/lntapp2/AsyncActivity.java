package com.example.lntapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class AsyncActivity extends AppCompatActivity {
    private static final String TAG = AsyncActivity.class.getSimpleName();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        progressBar = findViewById(R.id.progressBar);
    }

    public void handleClick(View view) {
        Log.i(TAG,"handleClick");
        //download something from internet
        DownloadTask downloadTask = new DownloadTask(progressBar);
        downloadTask.execute("https://urlForimagetobedownloaded");
        //show the download progress on the progress bar


    }
}