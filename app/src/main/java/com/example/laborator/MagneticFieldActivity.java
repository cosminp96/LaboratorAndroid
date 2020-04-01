package com.example.laborator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MagneticFieldActivity extends AppCompatActivity implements SensorEventListener {

    Toolbar toolbar;
    TextView txtX, txtY, txtZ;
    Sensor magnetic_field;
    SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_field);

        toolbar = findViewById(R.id.magneToolbar);
        txtX = (TextView)findViewById(R.id.magneX);
        txtY = (TextView)findViewById(R.id.magneY);
        txtZ = (TextView)findViewById(R.id.magneZ);

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        magnetic_field = SM.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetic_field != null)
        {
            SM.registerListener(this, magnetic_field, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            txtX.setText(R.string.magneNotSupported);
        }

        toolbar.setTitle(magnetic_field.getName());
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
