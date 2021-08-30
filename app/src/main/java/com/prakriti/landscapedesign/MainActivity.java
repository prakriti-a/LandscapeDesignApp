package com.prakriti.landscapedesign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// set landscape mode & save instance state

    private TextView txtInclude;
    private Button btnInclude;
    private CheckBox cbInclude;

    private static final int REQUEST_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // refer to UI components in included layout file
        txtInclude = findViewById(R.id.txtInclude);
        btnInclude = findViewById(R.id.btnInclude);
        cbInclude = findViewById(R.id.cbInclude);

        btnInclude.setOnClickListener(v -> {
            btnInclude.setText("THE BUTTON IS CLICKED");
            txtInclude.setText("THE TEXT IS CHANGED");
            cbInclude.setChecked(true);
            cbInclude.setText("THE CHECKBOX IS CHECKED");
        });

        cbInclude.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                txtInclude.setText("THE TEXT IS CHANGED");
                btnInclude.setText("THE BUTTON IS CLICKED");
                cbInclude.setText("THE CHECKBOX IS CHECKED");
            }
            else {
                txtInclude.setText("THIS IS MY TEXT");
                btnInclude.setText("THIS IS MY BUTTON");
                cbInclude.setText("THIS IS MY CHECKBOX");
            }
        });

        Button btnSwitch = findViewById(R.id.btnSwitch);
        btnSwitch.setOnClickListener(v -> {
            // pass data thru intent
            Intent i = new Intent(MainActivity.this, NextActivity.class);
            startActivityForResult(i, REQUEST_CODE); // we want some data back from NextActivity ->

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // can access the public static keys, OR put the actual key string value
            txtInclude.setText(data.getStringExtra(NextActivity.REQ_TEXT_TEXT));
            btnInclude.setText(data.getStringExtra(NextActivity.REQ_BUTTON_TEXT));
            cbInclude.setText(data.getStringExtra(NextActivity.REQ_CHECKBOX_TEXT));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // called after onPause Method (before onStop)
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_SHORT).show();

        outState.putString("TEXT_TEXT", txtInclude.getText().toString());
        outState.putString("BUTTON_TEXT", btnInclude.getText().toString());
        outState.putString("CHECKBOX_TEXT", cbInclude.getText().toString());
        outState.putBoolean("CHECKBOX_VALUE", cbInclude.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // called before onResume method (after onStart)
        Toast.makeText(this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show();

        txtInclude.setText(savedInstanceState.getString("TEXT_TEXT"));
        btnInclude.setText(savedInstanceState.getString("BUTTON_TEXT"));
        cbInclude.setText(savedInstanceState.getString("CHECKBOX_TEXT"));
        cbInclude.setChecked(savedInstanceState.getBoolean("CHECKBOX_VALUE"));
    }
}