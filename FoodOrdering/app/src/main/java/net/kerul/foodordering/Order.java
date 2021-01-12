package net.kerul.foodordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Order extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //spinner as object
        Spinner spinner = findViewById(R.id.phoneOptions);
        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
        }
        //provide the options of the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phoneoptions, android.R.layout.simple_spinner_item);
        //apply the adapter to the spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if(spinner!=null){
            spinner.setAdapter(adapter);
        }

    }

    public void onRadioClick(View view){
        boolean check = ((RadioButton) view).isChecked();
        if (view.getId()==R.id.sameday){
            Toast.makeText(this, "Sameday chosen", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId()==R.id.nextday){
            Toast.makeText(this, "Next chosen", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId()==R.id.pickup){
            Toast.makeText(this, "Pickup chosen", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}