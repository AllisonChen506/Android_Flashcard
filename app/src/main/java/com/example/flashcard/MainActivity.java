package com.example.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcardquestion)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcardanswer)).setText(allFlashcards.get(0).getAnswer());
        }
        findViewById(R.id.flashcardquestion).setVisibility(View.VISIBLE);
        findViewById(R.id.flashcardanswer).setVisibility(View.INVISIBLE);

        findViewById(R.id.flashcardquestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcardanswer).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcardquestion).setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcardquestion)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcardanswer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

        findViewById(R.id.flashcardanswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcardanswer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcardquestion).setVisibility(View.VISIBLE);
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
            ((TextView)findViewById(R.id.flashcardquestion)).setText(string1);
            ((TextView)findViewById(R.id.flashcardanswer)).setText(string2);
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }




}
