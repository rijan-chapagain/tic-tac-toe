package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class CheckWinner {

    protected boolean isGameWon(int boardCols, int boardRows, String[][] field) {
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
}