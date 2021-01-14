package net.kerul.sensorsignal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate sensor object
        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList=mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //string builder to hold sensor list
        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : sensorList ) {
            sensorText.append(currentSensor.getName()).append(
                    System.getProperty("line.separator"));
        }

        //pass list to the textview
        TextView tvsensor=(TextView)findViewById(R.id.sensorlist);
        tvsensor.setText(sensorText);
    }
}