package kerul.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView tvNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNumber=(TextView)findViewById(R.id.tvNumber);

    }
    public void showToast(View view){
        //log
        Log.e("MainActivity","Some debugging");
        //invoke the toast
        Toast toast=Toast.makeText(this,"BUTTON CLICKED", Toast.LENGTH_LONG);
        toast.show();
    }
    int counter=0;

    public void showCount(View view){
        counter++;
        tvNumber.setText(Integer.toString(counter));
    }
}