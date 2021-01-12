package net.kerul.passparameter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView tvpassedname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //container
        tvpassedname=(TextView)findViewById(R.id.tvPassedName);

        //capture the passed name value
        Intent intent = getIntent();
        String passedname=intent.getStringExtra("name");
        tvpassedname.setText(passedname);

    }
}