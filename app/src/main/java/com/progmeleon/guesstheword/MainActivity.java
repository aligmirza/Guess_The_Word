package com.progmeleon.guesstheword;// MainActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewWord;
    private TextView textViewWordLength;
    private EditText editTextGuess;
    private Button buttonCheckGuess;
    private Button buttonReset;
    private Button buttonNewGame;
    private Button buttonShowWord;
    private TextView textViewScore;

    private String currentWord;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWord = findViewById(R.id.textViewWord);
        textViewWordLength = findViewById(R.id.textViewWordLength);
        editTextGuess = findViewById(R.id.editTextGuess);
        buttonCheckGuess = findViewById(R.id.buttonCheckGuess);
        buttonReset = findViewById(R.id.buttonReset);
        buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonShowWord = findViewById(R.id.buttonShowWord);
        textViewScore = findViewById(R.id.textViewScore);

        // Initialize the game
        startNewGame();

        // Set click listeners for the buttons
        buttonCheckGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        buttonShowWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWord();
            }
        });
    }

    private void startNewGame() {
        // Get a random word from WordList
        currentWord = getRandomWord();

        // Display the first and last letters of the word
        displayPartialWord();

        // Reset score
        score = 0;
        updateScoreDisplay();
    }

    private void resetGame() {
        // Display the first and last letters of the word
        displayPartialWord();

        // Clear the guess input field
        editTextGuess.setText("");
    }

    private String getRandomWord() {
        int randomIndex = (int) (Math.random() * WordList.words.length);
        return WordList.words[randomIndex];
    }

    private void displayPartialWord() {
        if (currentWord.length() >= 2) {
            // Show the first and last letters
            String partialWord = currentWord.charAt(0) + " " + "_".repeat(currentWord.length() - 2) + " " + currentWord.charAt(currentWord.length() - 1);
            textViewWord.setText(partialWord);
        } else {
            // Handle words with less than 2 characters
            textViewWord.setText(currentWord);
        }

        // Display the length of the word
        String wordLengthText = "Word Length: " + currentWord.length();
        textViewWordLength.setText(wordLengthText);
    }

    private void checkGuess() {
        String userGuess = editTextGuess.getText().toString().toLowerCase();

        // Check if the guess is correct
        if (userGuess.equals(currentWord)) {
            // Increment the score
            score++;
            updateScoreDisplay();

            Toast.makeText(this, "Correct guess! Score +1", Toast.LENGTH_SHORT).show();

            // Clear the guess input field
            editTextGuess.setText("");

            // Start a new game after a correct guess
            startNewGame();
        } else {
            Toast.makeText(this, "Incorrect guess. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateScoreDisplay() {
        // Display the current score
        String scoreText = "Score: " + score;
        textViewScore.setText(scoreText);
    }

    private void showWord() {
        // Display the entire word
        textViewWord.setText(currentWord);
    }
}
