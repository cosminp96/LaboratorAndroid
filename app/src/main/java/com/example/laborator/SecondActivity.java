package com.example.laborator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mToolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.detailTextView);
        button = findViewById(R.id.button);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            String message = bundle.getString(Intent.EXTRA_TEXT);
            textView.setText(message);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setTitle("Help")
                        .setMessage("Was this helpful?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("second1", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("second2", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("second3", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("second4", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("second5", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("second6", "onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("product",mToolbar.getTitle().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getString("product"));
    }
}
