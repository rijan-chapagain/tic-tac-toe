package com.example.tictactoeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ComputerPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back_button;

    private int boardCols = 3;
    private int boardRows = boardCols;
    private Button[][] buttons = new Button[boardCols][boardRows];
    private boolean availableCells[][] = new boolean[boardCols][boardCols];

    private boolean isPlayer1Next = true;
    private int player1Points;
    private int player2Points;

    private int roundCount;

    private TextView textViewStatus;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;


    Utilities util = new Utilities();
    CheckWinner winner = new CheckWinner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_player);

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

        allowToClick(boardCols, boardRows);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Toast.makeText(getApplicationContext(),"Now onStart() calls", Toast.LENGTH_LONG).show();

        int orientation = getResources().getConfiguration().orientation;
        System.out.println("Device orientation is:  "+orientation);
        Toast.makeText(getApplicationContext(),"Orientation is:  " + orientation, Toast.LENGTH_LONG).show();

        // orientation 2 is landscape
        if(orientation == 2)
        {
            boardCols = 5;
            boardRows = 5;
            System.out.println("Orientation is landscape and col is 5");
        }
        else
        {
            boardCols = 3;
            boardRows = 3;
            System.out.println("Orientation is landscape and col is 3");
        }

        buttons = new Button[boardCols][boardRows];
        availableCells = new boolean[boardCols][boardCols];
        allowToClick(boardCols, boardRows);
        checkAvailableCells(boardCols, boardRows);
    }

    private void allowToClick(int boardCols, int boardRows){

        for (int i=0; i < boardCols; i++){
            for (int j=0; j < boardRows; j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    private void checkAvailableCells(int boardCols, int boardRows){
         if (roundCount != (boardCols * boardRows)-1) {
             System.out.println(boardCols+"   cols  Rows "+boardRows);

             for (int i = 0; i < boardCols; i++) {
                 for (int j = 0; j < boardRows; j++) {
                     System.out.println(i+" "+j);

                     if ((buttons[i][j].getText().toString().isEmpty())) {
                         availableCells[i][j] = true;
                     } else {
                         availableCells[i][j] = false;
                     }
                 }
             }
         }
    }

    private void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if (!((Button) view).getText().toString().equals("")) {
            Toast.makeText(this, "Already Filled!", Toast.LENGTH_SHORT).show();
            return;
        }

        checkAvailableCells(boardCols, boardRows);
        if (isPlayer1Next) {
            ((Button) view).setText("X");
        }

        roundCount++;
        checkWin();
    }

    private void checkWin(){
        String[][] field = new String [boardCols][boardRows];

        for (int i=0; i < boardCols; i++) {
            for (int j=0; j < boardRows; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        if (winner.isGameWon(boardCols, boardRows,field))
        {
            if(isPlayer1Next) {
                winnerIsPlayer1();
            }
            else {
                winnerIsPlayer2();
            }
        }
        else if (roundCount == boardCols * boardRows )
        {
            draw();
        }
        else {
            isPlayer1Next = !isPlayer1Next;

        }
        updateMessageText();
    }

    private void computerMove(){
        checkAvailableCells(boardCols, boardRows);
        int[] arr = util.ramdomIndex(boardCols, boardRows, availableCells);
        buttons[arr[0]][arr[1]].setText("O");
        roundCount++;
        checkWin();
        isPlayer1Next = true;
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
            checkAvailableCells(boardCols, boardRows);
            computerMove();
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
        isPlayer1Next = true;
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
        updateMessageText();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("player1Points", player1Points );
        outState.putInt("player2Points", player2Points );
        outState.putInt("roundCount", roundCount );
        outState.putBoolean("isPlayer1Next", isPlayer1Next);
    }
}