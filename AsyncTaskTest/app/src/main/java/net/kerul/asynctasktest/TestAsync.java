package net.kerul.asynctasktest;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class TestAsync extends AsyncTask<String, Integer, String> {
    private WeakReference<TextView> mTextView;

    TestAsync(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }
    @Override
    protected String doInBackground(String... strings) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;

        //timer
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
