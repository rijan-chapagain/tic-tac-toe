package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ComputerPlayer extends AppCompatActivity implements View.OnClickListener {
    private Button computer_back_button;
    private int boardCols = 3;
    private int boardRows = boardCols;
    private Button[][] buttons = new Button[boardCols][boardRows];

    private boolean player1Turn = true;
    private int player1Points;
    private int player2Points;

    private int roundCount;

    private TextView textViewStatus;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_player);

        // back to home button handler
        computer_back_button = (Button) findViewById(R.id.computer_back_button);
        computer_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });

        // text views
        textViewStatus = findViewById(R.id.text_view_status);
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i=0; i < boardCols; i++){
            for (int j=0; j < boardRows; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
//        game.checkClickedField(view);
//        game.setPlayersTurn(view);
        // set players turns
        if (player1Turn) {
            updateMessageText();
            ((Button) view).setText("X");
        }
        else {
            updateMessageText();
            ((Button) view).setText("O");
        }

        roundCount++;

        if (checkWinner()) {
            if(player1Turn) {
                winnerIsPlayer1();
            }
            else {
                winnerIsPlayer2();
            }
        }
        else if (roundCount ==9 ){
            draw();
        }
        else {
            player1Turn = !player1Turn;
        }
    }

    // check winner
    private boolean checkWinner() {
        String[][] field = new String [boardCols][boardRows];

        for (int i=0; i < boardCols; i++) {
            for (int j=0; j < boardRows; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // compare fields to eachother in columns
        for (int i=0; i < boardCols; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        // compare fields to eachother in rows
        for (int i=0; i < boardRows; i++) {
//            for(int j=0; j < i; j++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
//            }
        }

        // Compare diogonal
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        // Compare diogonal
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void winnerIsPlayer1() {
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        player1Points ++;
        updatePointsTable();
        resetBoard();
    }

    private void winnerIsPlayer2() {
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        player2Points ++;
        updatePointsTable();
        resetBoard();
    }

    private void updateMessageText() {
        if (player1Turn == true) {
            textViewStatus.setText("Current Status: Player1's Turn [X]");
        }
        else {
            textViewStatus.setText("Current Status: Player2's Turn [O]");
        }
    }

    private void updatePointsTable() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i=0; i < boardCols; i++) {
            for (int j=0; j < boardRows; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
        updateMessageText();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsTable();
        resetBoard();
    }
}