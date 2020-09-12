package com.example.tictactoeapp;

import androidx.annotation.NonNull;
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
                openComputerPlayerActivity();
            }
        });

        playWithHuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHumanPlayerActivity();
            }
        });
    }

    public void openComputerPlayerActivity(){
        Intent intent = new Intent(this, ComputerPlayerActivity.class);
        startActivity(intent);
    }

    public void openHumanPlayerActivity(){
        Intent intent = new Intent(this, HumanPlayerActivity.class);
        startActivity(intent);
    }

}