package com.august.hangman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {
    private Hangman hangman;
    private Button guessButton;
    private EditText guessEdit;
    private TextView infoText;
    private TextView guessedWord;
    private ImageView image;
    private Snackbar snackbar;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_play);
        shared = this.getSharedPreferences("GAME_INFO", Context.MODE_PRIVATE);

        if (b == null)
            hangman = new Hangman();
        else
            hangman = new Hangman(shared.getString("MYSTERYWORD",""),shared.getInt("ATTEMPTS_LEFT",10),
                    shared.getString("GUESSED_WORD",""),shared.getString("WRONG_LETTERS",""));

        guessButton = (Button)findViewById(R.id.guessButton);
        guessEdit = (EditText)findViewById(R.id.guessEdit);
        infoText = (TextView)findViewById(R.id.infoText);
        guessedWord = (TextView)findViewById(R.id.guessedWord);
        image = (ImageView)findViewById(R.id.image);

        update();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        shared = getSharedPreferences("GAME_INFO",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("MYSTERYWORD",hangman.getMysteryWord());
        editor.putInt("ATTEMPTS_LEFT",hangman.getAttemptsLeft());
        editor.putString("GUESSED_WORD",hangman.getGuessedWord());
        editor.putString("WRONG_LETTERS",hangman.getWrongLetters());
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Menu:
                Intent menuIntent = new Intent(this,MenuActivity.class);
                startActivity(menuIntent);
                return true;

            case R.id.action_Play:
                Intent playIntent = new Intent(this,PlayActivity.class);
                startActivity(playIntent);
                return true;

            case R.id.action_About:
                Intent aboutIntent = new Intent(this,AboutActivity.class);
                startActivity(aboutIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //Eventuellt AlertDialog eller Toast
    public void guessClicked(View view) {
        if (guessEdit.getText().length()<1 || guessEdit.getText().length()>1)
            snackbar.make(findViewById(R.id.guessEdit), R.string.length,
                    snackbar.LENGTH_SHORT).show();
        else if (hangman.alreadyGuessed(guessEdit.getText().charAt(0)))
            snackbar.make(findViewById(R.id.guessEdit), R.string.already,
                    snackbar.LENGTH_SHORT).show();
        else if (!Character.isLetter(guessEdit.getText().charAt(0)))
            snackbar.make(findViewById(R.id.guessEdit), R.string.isNotLetter,
                    snackbar.LENGTH_SHORT).show();
        else {
            char guess = Character.toLowerCase(guessEdit.getText().charAt(0));
            hangman.takeGuess(guess);
            if (hangman.hasWon())
                gameOver("won");
            if (hangman.getAttemptsLeft()<1)
                gameOver("lost");
            update();
        }
        guessEdit.setText("");
    }

    private void gameOver(String result) {
        Intent quitIntent = new Intent(this,ResultActivity.class);
        quitIntent.putExtra("RESULT",result);
        quitIntent.putExtra("MYSTERYWORD",hangman.getMysteryWord());
        startActivity(quitIntent);
    }

    private void updateInfoText() {
        String info = Integer.toString(hangman.getAttemptsLeft())+" attempts left"+'\n'+"Wrong letters: "+hangman.getWrongLetters();
        infoText.setText(info);
    }
    private void updateGuessedWord() {
        String word = hangman.getGuessedWord();
        guessedWord.setText(word);
    }
    private void updateImage() {
        String name = "hang"+hangman.getAttemptsLeft();
        int id = getResources().getIdentifier(name, "drawable", getPackageName());
        image.setImageResource(id);
    }
    private void update() {
        updateInfoText();
        updateGuessedWord();
        updateImage();
    }


}
