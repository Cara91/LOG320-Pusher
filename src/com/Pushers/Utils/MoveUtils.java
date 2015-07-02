package com.Pushers.Utils;

import com.Pushers.Bean.Board;
import com.Pushers.Bean.Move;

import java.util.List;

/**
 * Created by Gabriel on 2015-07-01.
 */
public class MoveUtils {
    public static int getRowNumber(int row){
        switch (row){
            case 1: return Board.ROW_1;
            case 2: return Board.ROW_2;
            case 3: return Board.ROW_3;
            case 4: return Board.ROW_4;
            case 5: return Board.ROW_5;
            case 6: return Board.ROW_6;
            case 7: return Board.ROW_7;
            case 8: return Board.ROW_8;
            default: return -1;
        }
    }

    public static int getColumnNumber(char letter){
        switch(letter){
            case 'A':	return Board.COLUMN_A;
            case 'B':	return Board.COLUMN_B;
            case 'C':	return Board.COLUMN_C;
            case 'D':	return Board.COLUMN_D;
            case 'E':	return Board.COLUMN_E;
            case 'F':	return Board.COLUMN_F;
            case 'G':	return Board.COLUMN_G;
            case 'H':	return Board.COLUMN_H;
            default:	return -1;
        }
    }

    public static void printMoveList(List<Move> moveList){
        for (int i = 0; i < moveList.size(); i++) {

            if(i % 10 == 0)
                System.out.println("");

            System.out.print(moveList.get(i) + ", ");
        }
        System.out.println();
    }
}
