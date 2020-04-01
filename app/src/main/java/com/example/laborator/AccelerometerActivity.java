package com.example.laborator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    Toolbar toolbar;
    TextView txtX, txtY, txtZ;
    Sensor accelerometer;
    SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        txtX = (TextView)findViewById(R.id.acceX);
        txtY = (TextView)findViewById(R.id.acceY);
        txtZ = (TextView)findViewById(R.id.acceZ);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null)
        {
            SM.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            txtX.setText(R.string.acceNotSupported);
        }

        toolbar = findViewById(R.id.acceToolbar);
        toolbar.setTitle(accelerometer.getName());
        setSupportActionBar(toolbar);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txtX.setText("X: " + event.values[0]);
        txtY.setText("Y: " + event.values[1]);
        txtZ.setText("Z: " + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //not used
    }
}
