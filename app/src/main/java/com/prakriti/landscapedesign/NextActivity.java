package com.prakriti.landscapedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NextActivity extends AppCompatActivity {

    public static final String REQ_TEXT_TEXT = "REQ_TEXT";
    public static final String REQ_BUTTON_TEXT = "REQ_BUTTON";
    public static final String REQ_CHECKBOX_TEXT = "REQ_CHECKBOX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        EditText edtCheckbox = findViewById(R.id.edtCheckbox);
        EditText edtButton = findViewById(R.id.edtButton);
        EditText edtText = findViewById(R.id.edtText);

        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(v -> {
            // send data back to MainActivity
            Intent data = new Intent();
            // pass key-value pairs of the data
            data.putExtra(REQ_TEXT_TEXT, edtText.getText().toString());
            data.putExtra(REQ_BUTTON_TEXT, edtButton.getText().toString());
            data.putExtra(REQ_CHECKBOX_TEXT, edtCheckbox.getText().toString());
            setResult(RESULT_OK, data);
            finish(); // will close this activity and go back, so no need to specify this & class in intent onject

        });


    }
}