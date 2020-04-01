package com.example.laborator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class GyroActivity extends AppCompatActivity implements SensorEventListener {

    Toolbar toolbar;
    TextView txtX, txtY, txtZ;
    Sensor gyroscope;
    SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);

        toolbar = findViewById(R.id.gyroToolbar);
        txtX = (TextView)findViewById(R.id.gyroX);
        txtY = (TextView)findViewById(R.id.gyroY);
        txtZ = (TextView)findViewById(R.id.gyroZ);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null)
        {
            SM.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            txtX.setText(R.string.gyroNotSupported);
        }

        toolbar.setTitle(gyroscope.getName());
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
