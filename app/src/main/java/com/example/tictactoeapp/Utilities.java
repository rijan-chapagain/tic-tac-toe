package com.example.tictactoeapp;

import java.util.Random;

public class Utilities {

    private int randIndex[] = new int[2];
    Random rd = new Random();

   public int[] ramdomIndex(int boardCols, int boardRows, boolean availableCells[][] ){
        randIndex[0] = rd.nextInt(boardCols);
        randIndex[1] = rd.nextInt(boardRows);

        do {
            System.out.println(randIndex[0] +" " + randIndex[1]);
            if (availableCells[randIndex[0]][randIndex[1]]) {
                int[] arr={randIndex[0],randIndex[1]};
                return arr;
            } else {
                randIndex[0] = rd.nextInt(boardCols);
                randIndex[1] = rd.nextInt(boardRows);
            }
        } while (true);
    }
}
