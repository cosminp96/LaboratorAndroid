package com.example.laborator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.detailTextView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("ProductName"));
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_1")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_2")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_3")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_4")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_5")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_6")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_7")) {
                textView.setText(bundle.getString("ProductName"));
            }
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Product_8")) {
                textView.setText(bundle.getString("ProductName"));
            }
        }
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
