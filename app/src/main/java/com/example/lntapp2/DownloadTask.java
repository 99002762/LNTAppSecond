package com.example.lntapp2;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

//input type, progress type,result type
public class DownloadTask  extends AsyncTask<String,Integer,Void> {

    ProgressBar mProgressBar;
    private static final String TAG = DownloadTask.class.getSimpleName();

    public DownloadTask(ProgressBar progressBar) {
        mProgressBar = progressBar;

    }

    /**
     * this method run on a seperate thread
     * @param strings
     * @return
     */
    @Override
    protected Void doInBackground(String... strings) {

        Log.i(TAG,"doInBackground"+strings[0]);
        publishProgress(50);
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.setProgress(values[0]);
    }
}
