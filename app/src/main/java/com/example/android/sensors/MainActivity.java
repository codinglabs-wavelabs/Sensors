package com.example.android.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sensorManager = null;
    TextView textView = null;
    List list;

    SensorEventListener sensorEventListener = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Get a SensorManager instance */
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        textView = (TextView)findViewById(R.id.textView);

        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0){
            sensorManager.registerListener(sensorEventListener, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        if(list.size()>0){
            sensorManager.unregisterListener(sensorEventListener);
        }
        super.onStop();
    }
}
