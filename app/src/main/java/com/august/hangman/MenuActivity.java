package com.august.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button playButton;
    private Button aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        playButton = (Button)findViewById(R.id.playButton);
        aboutButton = (Button)findViewById(R.id.aboutButton);
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

    public void clickedPlay(View view) {
        Intent intentPlay = new Intent(this,PlayActivity.class);
        startActivity(intentPlay);
    }

    public void clickedAbout(View view) {
        Intent intentAbout = new Intent(this,AboutActivity.class);
        startActivity(intentAbout);
    }
}
