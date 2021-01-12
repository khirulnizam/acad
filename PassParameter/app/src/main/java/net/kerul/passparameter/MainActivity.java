package net.kerul.passparameter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtname=(EditText)findViewById(R.id.txtName);
    }
    //public static final String EXTRA_MESSAGE="key";

    public void launch2ndActivity(View view){

        //Log.e();
        String message=txtname.getText().toString();
        Toast toast=Toast.makeText(this,message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP| Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        //invoke 2nd screen here
        Intent intent2nd=new Intent(this,SecondActivity.class);
        intent2nd.putExtra("name",message);
        startActivity(intent2nd);
    }
}