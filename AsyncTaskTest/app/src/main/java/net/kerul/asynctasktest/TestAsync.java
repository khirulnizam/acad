package net.kerul.asynctasktest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class TestAsync extends AsyncTask<String, Integer, String> {
    private WeakReference<TextView> mTextView;
    private ProgressDialog dialog;

    TestAsync(TextView tv, MainActivity activity) {
        mTextView = new WeakReference<>(tv);
        //progressDialog
        dialog = new ProgressDialog(activity);
    }

    //before AsyncTask
    protected void onPreExecute(){
        dialog.setMessage("Hold on ...");
        dialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 500;

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
        //dismiss dialog progress
        if (dialog.isShowing()) {//check progress dialog on?
            dialog.dismiss();
        }
    }

}
