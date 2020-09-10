package com.example.tictactoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HumanPlayer extends AppCompatActivity implements View.OnClickListener {
    private Button back_button;
    private int boardCols = 3;
    private int boardRows = boardCols;
    private Button[][] buttons = new Button[boardCols][boardRows];

    private boolean isPlayer1Next = true;
    private int player1Points;
    private int player2Points;

    private int roundCount;

    private TextView textViewStatus;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private int orientation;

    CheckWinner winner = new CheckWinner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_player);

        // back to home button handler
        back_button = (Button) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();
            }
        });

        // text views
        textViewStatus = findViewById(R.id.text_view_status);
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        printBoard();

        int orientation = getResources().getConfiguration().orientation;

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }

    public void printBoard(){
        for (int i=0; i < boardCols; i++){
            for (int j=0; j < boardRows; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    public void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            Toast.makeText(this, "Already Filled!", Toast.LENGTH_SHORT).show();

            return;
        }

        if (isPlayer1Next) {
            ((Button) view).setText("X");
        }
        else {
            ((Button) view).setText("O");
        }

        roundCount++;

//        String[][] field = new String [boardCols][boardRows];
//        System.out.println("Cols:  " +boardCols+ " Rows:  "+ boardRows);
//        for (int i=0; i < boardCols; i++) {
//            for (int j=0; j < boardRows; j++){
//                System.out.println(i +"  ij  "+ j);
//                field[i][j] = buttons[i][j].getText().toString();
//            }
//        }

//        if (winner.isGameWon(boardCols, boardRows, field)) {
        if(checkWinner()){
            if(isPlayer1Next) {
                winnerIsPlayer1();
            }
            else {
                winnerIsPlayer2();
            }
        }
        else if (roundCount == boardCols * boardRows ){
            draw();
        }
        else {
            isPlayer1Next = !isPlayer1Next;
        }
        updateMessageText();
    }

    private boolean checkWinner(){
        printBoard();

        String[][] field = new String [boardCols][boardRows];
        System.out.println("Cols:  " +boardCols+ " Rows:  "+ boardRows);
        for (int i=0; i < boardCols; i++) {
            for (int j=0; j < boardRows; j++){
                System.out.println(i +"  ij  "+ j);
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        if(boardCols == 3 && boardRows == 3){
            System.out.println(boardRows);
            // compare fields to eachother in columns
            for (int i = 0; i < boardCols; i++) {
                if (field[i][0].equals(field[i][1])
                        && field[i][0].equals(field[i][2])
                        && !field[i][0].equals("")) {
                    return true;
                }
            }

            // compare fields to eachother in rows
            for (int i = 0; i < boardRows; i++) {
                if (field[0][i].equals(field[1][i])
                        && field[0][i].equals(field[2][i])
                        && !field[0][i].equals("")) {
                    return true;
                }
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

        else{
            // compare fields to eachother in columns
            System.out.println(boardRows);
            for (int i = 0; i < boardCols; i++) {
                if (field[i][0].equals(field[i][1])
                        && field[i][0].equals(field[i][2])
                        && !field[i][0].equals("")
                        && (field[i][0].equals(field[i][3])
                        && field[i][0].equals(field[i][4]))
                ) {
                    return true;
                }
            }

            // compare fields to eachother in rows
            for (int i = 0; i < boardRows; i++) {
                if (field[0][i].equals(field[1][i])
                        && field[0][i].equals(field[2][i])
                        && !field[0][i].equals("")
                        && field[0][i].equals(field[3][i])
                        && field[0][i].equals(field[4][i])
                ) {
                    return true;
                }
            }

            // Compare diogonal
            if (field[0][0].equals(field[1][1])
                    && field[0][0].equals(field[2][2])
                    && !field[0][0].equals("")
                    && field[0][0].equals(field[3][3])
                    && field[0][0].equals(field[4][4])
            ) {
                return true;
            }

            // Compare diogonal
            if (field[0][4].equals(field[1][3])
                    && field[0][4].equals(field[2][2])
                    && !field[0][4].equals("")
                    && field[0][4].equals(field[3][1])
                    && field[0][4].equals(field[4][0])
            ) {
                return true;
            }
            return false;
        }
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
        if (isPlayer1Next == true) {
            textViewStatus.setText("Next Turn: Player1's Turn [X]");
        }
        else {
            textViewStatus.setText("Next Turn: Player2's Turn [O]");
        }
    }

    private void updatePointsTable() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    protected void resetBoard() {
        for (int i=0; i < boardCols; i++) {
            for (int j=0; j < boardRows; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        isPlayer1Next = true;
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    protected void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsTable();
        resetBoard();
        updateMessageText();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("orientation:   "+orientation);

        outState.putInt("roundCount", 0);
        outState.putInt("player1Points", 6);
        outState.putInt("player2Points", 0);
        outState.putBoolean("player1Turn", isPlayer1Next);

        if(orientation == 2) {
            outState.putInt("boardCols", boardCols = 3);
            outState.putInt("boardRows", boardRows = 3);
        }
        else {
            outState.putInt("boardCols", boardCols = 5);
            outState.putInt("boardRows", boardRows = 5);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        boardCols = savedInstanceState.getInt("boardCols");
        boardRows = savedInstanceState.getInt("boardRows");
        isPlayer1Next = savedInstanceState.getBoolean("player1Turn");
    }
}