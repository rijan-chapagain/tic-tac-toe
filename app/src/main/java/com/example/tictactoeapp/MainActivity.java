package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button playWithComputer;
    private Button playWithHuman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playWithComputer = (Button) findViewById(R.id.playWithComputer);
        playWithHuman = (Button) findViewById(R.id.playWithHuman);

        playWithComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openComputerPlayer();
            }
        });

        playWithHuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHumanPlayer();
            }
        });
    }

    public void openComputerPlayer(){
        Intent intent = new Intent(this, ComputerPlayer.class);
        startActivity(intent);
    }

    public void openHumanPlayer(){
        Intent intent = new Intent(this, HumanPlayer.class);
        startActivity(intent);
    }
}