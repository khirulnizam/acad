package net.kerul.asynctasktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI to bind with R.id.widget
        mTextView = findViewById(R.id.textView);
    }

    public void startTask(View view){
        //call the AsyncTask
        new TestAsync(mTextView).execute();
    }

}