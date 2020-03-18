package com.example.laborator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.laborator.MESSAGE";
    private ListView mainListView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        mainListView = findViewById(R.id.mainListView);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.ProductName));

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, mainListView.getItemAtPosition(position).toString());
                startActivity(Intent.createChooser(intent,"Choose app:"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Do you want to change the title?");

                final EditText newTitle = new EditText(MainActivity.this);
                newTitle.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(newTitle);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toolbar = findViewById(R.id.toolbar);
                        toolbar.setTitle(newTitle.getText().toString());
                        Toast.makeText(MainActivity.this, "Title changed", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            }
            case R.id.item2: {
                //settings
                AlertDialog.Builder info = new AlertDialog.Builder(MainActivity.this);
                String message = "This app was created by Cosmin Popescu and it is intended to be a school laboratory assignment.";
                info.setTitle("Informations");
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
            case R.id.item3: {
                //more
                return true;
            }
            case R.id.subitem1:{
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
                android.os.Process.killProcess(android.os.Process.myPid());
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
}
