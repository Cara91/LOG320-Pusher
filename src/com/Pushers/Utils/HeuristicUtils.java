package com.Pushers.Utils;

import com.Pushers.Bean.Board;

/**
 * Created by Gabriel on 2015-07-12.
 */
public class HeuristicUtils {
    public static final int PUSHER_VALUE = 10;
    public static final int PUSHABLE_VALUE = 5;

    private static final int COLUMN_A_MULTIPLIER = 10;
    private static final int COLUMN_B_MULTIPLIER = 8;
    private static final int COLUMN_C_MULTIPLIER = 6;
    private static final int COLUMN_D_MULTIPLIER = 4;
    private static final int COLUMN_E_MULTIPLIER = 4;
    private static final int COLUMN_F_MULTIPLIER = 6;
    private static final int COLUMN_G_MULTIPLIER = 8;
    private static final int COLUMN_H_MULTIPLIER = 10;

    private static final int ROW_1_WHITE_MULTIPLIER = 0;
    private static final int ROW_2_WHITE_MULTIPLIER = 1;
    private static final int ROW_3_WHITE_MULTIPLIER = 2;
    private static final int ROW_4_WHITE_MULTIPLIER = 3;
    private static final int ROW_5_WHITE_MULTIPLIER = 4;
    private static final int ROW_6_WHITE_MULTIPLIER = 6;
    private static final int ROW_7_WHITE_MULTIPLIER = 8;
    private static final int ROW_8_WHITE_MULTIPLIER = 1000;

    private static final int ROW_1_BLACK_MULTIPLIER = 1000;
    private static final int ROW_2_BLACK_MULTIPLIER = 8;
    private static final int ROW_3_BLACK_MULTIPLIER = 6;
    private static final int ROW_4_BLACK_MULTIPLIER = 4;
    private static final int ROW_5_BLACK_MULTIPLIER = 3;
    private static final int ROW_6_BLACK_MULTIPLIER = 2;
    private static final int ROW_7_BLACK_MULTIPLIER = 1;
    private static final int ROW_8_BLACK_MULTIPLIER = 0;

    public static int positionMultiplier(int row, int column, boolean isWhite){
        return columnMultiplier(column)*rowMultiplier(row, isWhite);
    }

    private static int rowMultiplier(int row, boolean isWhite){
        if(isWhite) {
            switch (row){
                case Board.ROW_1:
                    return ROW_1_WHITE_MULTIPLIER;
                case Board.ROW_2:
                    return ROW_2_WHITE_MULTIPLIER;
                case Board.ROW_3:
                    return ROW_3_WHITE_MULTIPLIER;
                case Board.ROW_4:
                    return ROW_4_WHITE_MULTIPLIER;
                case Board.ROW_5:
                    return ROW_5_WHITE_MULTIPLIER;
                case Board.ROW_6:
                    return ROW_6_WHITE_MULTIPLIER;
                case Board.ROW_7:
                    return ROW_7_WHITE_MULTIPLIER;
                case Board.ROW_8:
                    return ROW_8_WHITE_MULTIPLIER;
                default:
                    return 0;
            }
        }else{
            switch (row){
                case Board.ROW_1:
                    return ROW_1_BLACK_MULTIPLIER;
                case Board.ROW_2:
                    return ROW_2_BLACK_MULTIPLIER;
                case Board.ROW_3:
                    return ROW_3_BLACK_MULTIPLIER;
                case Board.ROW_4:
                    return ROW_4_BLACK_MULTIPLIER;
                case Board.ROW_5:
                    return ROW_5_BLACK_MULTIPLIER;
                case Board.ROW_6:
                    return ROW_6_BLACK_MULTIPLIER;
                case Board.ROW_7:
                    return ROW_7_BLACK_MULTIPLIER;
                case Board.ROW_8:
                    return ROW_8_BLACK_MULTIPLIER;
                default:
                    return 0;
            }
        }
    }
    
    private static int columnMultiplier(int column){
        switch (column){
            case Board.COLUMN_A:
                return COLUMN_A_MULTIPLIER;
            case Board.COLUMN_B:
                return COLUMN_B_MULTIPLIER;
            case Board.COLUMN_C:
                return COLUMN_C_MULTIPLIER;
            case Board.COLUMN_D:
                return COLUMN_D_MULTIPLIER;
            case Board.COLUMN_E:
                return COLUMN_E_MULTIPLIER;
            case Board.COLUMN_F:
                return COLUMN_F_MULTIPLIER;
            case Board.COLUMN_G:
                return COLUMN_G_MULTIPLIER;
            case Board.COLUMN_H:
                return COLUMN_H_MULTIPLIER;
            default:
                return 0;
        }
    }
}
