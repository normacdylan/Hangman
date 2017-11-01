package com.august.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView message;
    private Button playAgainButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        message = (TextView)findViewById(R.id.message);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        backButton = (Button)findViewById(R.id.backMenuButton);
        setMessage();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
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

    private void setMessage() {
        Intent i = getIntent();
        if (i.getStringExtra("RESULT").equals("won"))
            message.setText("Congratulations! You Won!");
        else
            message.setText("You Lost!"+'\n'+"The correct word was "+i.getStringExtra("MYSTERYWORD")+".");
        }
    public void clickedAgain(View view) {
        Intent playAgain = new Intent(this,PlayActivity.class);
        startActivity(playAgain);
    }
    public void clickedBack(View view) {
        Intent back = new Intent(this,MenuActivity.class);
        startActivity(back);
    }

}
