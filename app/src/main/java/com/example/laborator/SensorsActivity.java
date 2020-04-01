package com.example.laborator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

public class SensorsActivity extends AppCompatActivity{

    ListView list;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        final SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        toolbar = findViewById(R.id.sensorToolbar);
        toolbar.setTitle(getResources().getString(R.string.sensorsTitle));
        setSupportActionBar(toolbar);

        list = findViewById(R.id.sensorsListView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:{
                        startActivity(new Intent(SensorsActivity.this, AccelerometerActivity.class));
                        break;
                    }
                    case 1:{
                        startActivity(new Intent(SensorsActivity.this, GyroActivity.class));
                        break;
                    }
                    case 2:{
                        startActivity(new Intent(SensorsActivity.this, MagneticFieldActivity.class));
                        break;
                    }
                    default: {
                        AlertDialog.Builder info = new AlertDialog.Builder(SensorsActivity.this);
                        info.setTitle("Error 404!");
                        info.setMessage(R.string.notImplementedMessage);
                        info.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        info.show();
                        break;
                    }
                }

            }
        });
        list.setAdapter(new MySensorAdapter(this,R.layout.simplerow, sensors));
    }
}
