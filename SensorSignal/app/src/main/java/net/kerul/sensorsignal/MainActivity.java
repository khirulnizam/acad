package net.kerul.sensorsignal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorProximity;
    private TextView mTextSensorProximity;

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }//onstart
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }//app is stopped

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to display proximity sensors
        mTextSensorProximity = (TextView) findViewById(R.id.tvproximity);
        //mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        String sensor_error = "Light sensor error";
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float currentValue = event.values[0];
        if (sensorType==Sensor.TYPE_PROXIMITY){
            mTextSensorProximity.setText("Proximity:"+currentValue);
        }
        else if (sensorType==Sensor.TYPE_LIGHT){
            mTextSensorProximity.setText("Light sensor:"+currentValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //int sensorType = event.sensor.getType();
    }
}