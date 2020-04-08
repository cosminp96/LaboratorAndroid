package com.example.laborator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.laborator.MESSAGE";
    private ListView mainListView;
    Toolbar toolbar;
    boolean performSync;
    String syncInterval;
    String fullName;
    String email;
    String mainTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 124);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        mainListView = findViewById(R.id.mainListView);



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        this.performSync = prefs.getBoolean("perform_sync", true);
        this.syncInterval = prefs.getString("sync_interval", "30");
        this.fullName = prefs.getString("full_name", "");
        this.email = prefs.getString("email_address", "");
        this.mainTitle = prefs.getString("main_title", "");


        toolbar.setTitle(mainTitle);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.ProductName));

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, mainListView.getItemAtPosition(position).toString());
                startActivity(Intent.createChooser(intent, "Choose app:"));
            }
        });
        mainListView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("first1", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("first2", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("first3", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("first4", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("first5", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("first6", "onDestroy");
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 1000:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "@string/permissionYes", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "@string/permissionNo", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                break;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSave: {
                //save preferences as string
                String preferences = settingsToText();
                //save preferences
                save("settingsFile.txt", preferences);
                return true;
            }
            case R.id.item1: {
                //actual preferences
                startActivity(new Intent(this, Settings.class));
                return true;
            }
            case R.id.item2: {
                //information
                AlertDialog.Builder info = new AlertDialog.Builder(MainActivity.this);
                String message = "This app was created by Cosmin Popescu and it is intended to be a school laboratory assignment.";
                info.setTitle("Information");
                info.setMessage(message);
                info.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                info.show();
                return true;
            }
            case R.id.itemSensors:{
                startActivity(new Intent(this, SensorsActivity.class));
                return true;
            }
            case R.id.itemCamera:{
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission not granted", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 124);
                    return false;
                } else {
                    startActivity(new Intent(this, CameraActivity.class));
                    return true;
                }
            }
            case R.id.itemLocation:{
                //current location coordinates
                GPStracker gpstracker = new GPStracker(getApplicationContext());
                Location l = gpstracker.getLocation();
                if(l != null){
                    double latitude = l.getLatitude();
                    double longitude = l.getLongitude();
                    AlertDialog.Builder currentLocation = new AlertDialog.Builder(MainActivity.this);
                    String message = "Latitude: " + latitude + "\n" + "Longitude: " + longitude + "\n";
                    currentLocation.setTitle("Current Location");
                    currentLocation.setMessage(message);
                    currentLocation.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    currentLocation.show();
                }
                return true;
            }
            case R.id.item3: {
                //more
                return true;
            }
            case R.id.subitem1: {
//                Toast.makeText(this, "Rate app option selected", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Do you enjoy our app?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder buttonYes = new AlertDialog.Builder(MainActivity.this);
                        buttonYes.setTitle("Rate yes");
                        buttonYes.setMessage("Thank you for your feedback!");
                        buttonYes.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                            }
                        });
                        buttonYes.show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder buttonNo = new AlertDialog.Builder(MainActivity.this);
                        buttonNo.setTitle("Rate No");
                        buttonNo.setMessage("We are sorry to hear that. We'll still work to make your experience better.");
                        buttonNo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        buttonNo.show();
                    }
                });
                builder.show();
                return true;
            }
            case R.id.subitem2: {
                Process.killProcess(Process.myPid());
                System.exit(1);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void save(String fileName, String text) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this,"Saved to " + getFilesDir() + "/" + fileName,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> load(String fileName) {
        FileInputStream fis = null;
        List<String> settings = null;
        String content = "";
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
                settings.add(text);
            }

            content = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return settings;
    }

    public String settingsToText()
    {
        String result = "";

        result += this.performSync + "\n";
        result += this.syncInterval + "\n";
        result += this.fullName + "\n";
        result += this.email + "\n";
        result += this.mainTitle + "\n";

        return result;
    }
}
