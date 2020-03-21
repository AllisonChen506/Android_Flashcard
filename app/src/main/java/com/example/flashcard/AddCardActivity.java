package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(AddCardActivity.this, MainActivity.class); // create a new Intent, this is where we will put our data
                data.putExtra("string1", ((EditText)findViewById(R.id.question)).getText().toString());
                data.putExtra("string2", ((EditText)findViewById(R.id.answer)).getText().toString());
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });
    }

}
