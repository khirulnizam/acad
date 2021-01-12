package net.kerul.foodordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;

public class DatePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
    }

    //function called by burron click from Activity
    public void showDatePicker(){
        DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getActivity(), this, year,month,day);
    }
}