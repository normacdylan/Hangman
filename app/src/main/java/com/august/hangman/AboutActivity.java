package com.august.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backButton = (Button)findViewById(R.id.backButton);
    }



    public void clickedBack(View view) {
        Intent backIntent = new Intent(this,MenuActivity.class);
        startActivity(backIntent);
    }
}
