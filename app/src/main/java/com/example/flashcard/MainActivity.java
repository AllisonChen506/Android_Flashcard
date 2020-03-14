package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.flashcardquestion).setVisibility(View.VISIBLE);
        findViewById(R.id.flashcardanswer).setVisibility(View.INVISIBLE);

        findViewById(R.id.flashcardquestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcardanswer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardquestion).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 & data != null) {
            String string1 = data.getExtras().getString("string1", "What color are guavas?");
            String string2 = data.getExtras().getString("string2", "green");
        }
    }
}
