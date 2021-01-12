package net.kerul.foodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerActivity extends AppCompatActivity {
    DatePicker datepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        //date picker
        datepicker=(DatePicker)findViewById(R.id.datePicker1);
    }

    //
    public void simpledatepick(View view){
        String date = datepicker.getDayOfMonth()+"/"+(datepicker.getMonth()+1)+"/"+datepicker.getYear();
        Toast.makeText(this,date,Toast.LENGTH_SHORT).show();
    }

    //function called by burron click from Activity
    public void showDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
}