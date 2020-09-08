package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HumanPlayer extends AppCompatActivity {
    private Button human_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_player);

        human_back_button = (Button) findViewById(R.id.human_back_button);
        human_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}