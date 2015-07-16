package com.Pushers.Utils;

import com.Pushers.Bean.Board;

/**
 * Created by Gabriel on 2015-07-12.
 */
public class HeuristicUtils {
    public static final int PUSHER_VALUE = 2;
    public static final int PUSHABLE_VALUE = 1;

    private static final int COLUMN_A_MULTIPLIER = 4;
    private static final int COLUMN_B_MULTIPLIER = 3;
    private static final int COLUMN_C_MULTIPLIER = 2;
    private static final int COLUMN_D_MULTIPLIER = 2;
    private static final int COLUMN_E_MULTIPLIER = 2;
    private static final int COLUMN_F_MULTIPLIER = 2;
    private static final int COLUMN_G_MULTIPLIER = 3;
    private static final int COLUMN_H_MULTIPLIER = 4;

    private static final int ROW_1_WHITE_MULTIPLIER = 4;
    private static final int ROW_2_WHITE_MULTIPLIER = 3;
    private static final int ROW_3_WHITE_MULTIPLIER = 2;
    private static final int ROW_4_WHITE_MULTIPLIER = 4;
    private static final int ROW_5_WHITE_MULTIPLIER = 6;
    private static final int ROW_6_WHITE_MULTIPLIER = 10;
    private static final int ROW_7_WHITE_MULTIPLIER = 100;
    private static final int ROW_8_WHITE_MULTIPLIER = 10000;

    private static final int ROW_1_BLACK_MULTIPLIER = 10000;
    private static final int ROW_2_BLACK_MULTIPLIER = 100;
    private static final int ROW_3_BLACK_MULTIPLIER = 10;
    private static final int ROW_4_BLACK_MULTIPLIER = 6;
    private static final int ROW_5_BLACK_MULTIPLIER = 4;
    private static final int ROW_6_BLACK_MULTIPLIER = 2;
    private static final int ROW_7_BLACK_MULTIPLIER = 3;
    private static final int ROW_8_BLACK_MULTIPLIER = 4;

    public static int canEat(int row, int column, boolean isWhite, int[][] board, boolean isPusher){
        int score = 0;
        int behind = (isWhite ? -1 : 1);
        int aHead = (isWhite ? 1 : -1);
        int left = -1;
        int right = 1;
        if((row == 0 && !isWhite) || (row == 7 && isWhite)) {
            if (!isPusher) {
                if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column]) && BoardUtils.isAPusher(board[row + behind][column]) && !notAFriendlyPieceAndNotEmpty(isWhite, board[row + behind][column])) {
                    score += 50;
                }
                if (column != 0 && column != 7) {
                    if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column + left]) && BoardUtils.isAPusher(board[row + behind][column + right]) && !notAFriendlyPieceAndNotEmpty(isWhite, board[row + behind][column + right])) {
                        score += 50;
                    }
                    if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column + right]) && BoardUtils.isAPusher(board[row + behind][column + left]) && !notAFriendlyPieceAndNotEmpty(isWhite, board[row + behind][column + left])) {
                        score += 50;
                    }
                }
            } else {
                if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column])) {
                    score += 50;
                }
                if (column == 0) {
                    if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column + right])) {
                        score += 50;
                    }
                } else if (column == 7) {
                    if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column + left])) {
                        score += 50;
                    }
                } else {
                    if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column + left])) {
                        score += 50;
                    }
                    if (notAFriendlyPieceAndNotEmpty(isWhite, board[row + aHead][column + right])) {
                        score += 50;
                    }
                }
            }
        }
        return score;
    }

    public static int canBeEat(int row, int column, boolean isWhite, int[][] board, boolean isPusher){
        int score = 0;
        if((row == 0 && !isWhite) || (row == 7 && isWhite)) {
            if(isPusher){
                if(canBeEatOnTheLeft(row, column, board, isWhite)){
                    score += -5000;
                }
                if(canBeEatOnTheRight(row, column, board, isWhite)){
                    score += -5000;
                }
            }else{
                if(canBeEatOnTheLeft(row, column, board, isWhite)){
                    score += -1000;
                }
                if(canBeEatOnTheRight(row, column, board, isWhite)){
                    score += -1000;
                }
            }
        }
        return score;
    }

    private static boolean canBeEatOnTheLeft(int row, int column, int[][] board, boolean isWhite){
        int furtherAHead = (isWhite ? -2 : 2);
        int aHead = (isWhite ? 1 : -1);
        int left = -1;
        int furtherLeft = -2;
        if (column == 0) {
            return false;
        } else if (column == 1) {
            if(notAFriendlyPieceAndNotEmpty(isWhite, board[row+aHead][column+left]) && BoardUtils.isAPusher(board[row+aHead][column+left])){
                return true;
            }
        }else{
            if(notAFriendlyPieceAndNotEmpty(isWhite, board[row+aHead][column+left])){
                if(BoardUtils.isAPusher(board[row+aHead][column+left])){
                    return true;
                }else{
                    if(BoardUtils.isAPusher(board[row+furtherAHead][column+furtherLeft])){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean canBeEatOnTheRight(int row, int column, int[][] board, boolean isWhite){
        int furtherAHead = (isWhite ? -2 : 2);
        int aHead = (isWhite ? 1 : -1);
        int right = 1;
        int furtherRight = 2;
        return false;
    }


    /*
    Peut-etre a revoir puisqu'elle fait literallement ce le nom de la fonction

    si il y a un pusher plus loin qui peut le bouger la fonction retourn -9999
     */
    public static int ableToMove(int row, int column, boolean isWhite, int[][] board){
        int score = 0;
        if(row<7 && row>0) {
            int behind = (isWhite ? -1 : 1);
            int aHead = (isWhite ? 1 : -1);
            int left = -1;
            int right = 1;

            if (BoardUtils.isAPusher(board[row + behind][column]) && notAFriendlyPiece(isWhite, board[row + aHead][column])) {
                score += 2;
            }

            if (column != 0 && column != 7) {
                if (BoardUtils.isAPusher(board[row + behind][column + right]) && notAFriendlyPiece(isWhite, board[row + aHead][column + left])) {
                    score += 1;
                }
                if (BoardUtils.isAPusher(board[row + behind][column + left]) && notAFriendlyPiece(isWhite, board[row + aHead][column + right])) {
                    score += 1;
                }
            }
        }
        return score;
    }

    private static boolean notAFriendlyPiece(boolean isWhite, int state){
        if(isWhite){
            if(BoardUtils.isEmpty(state) || !BoardUtils.isWhite(state)){
                return true;
            }
        }else{
            if(BoardUtils.isEmpty(state) || BoardUtils.isWhite(state)){
                return true;
            }
        }
        return false;
    }

    private static boolean notAFriendlyPieceAndNotEmpty(boolean isWhite, int state){
        if(isWhite){
            if(!BoardUtils.isEmpty(state) || !BoardUtils.isWhite(state)){
                return true;
            }
        }else{
            if(!BoardUtils.isEmpty(state) || BoardUtils.isWhite(state)){
                return true;
            }
        }
        return false;
    }

    public static int isBlocking(int row, int column, boolean isWhite, int[][] board){
        if(row<7 && row>0) {
            int aHead = (isWhite ? 1 : -1);
            if (BoardUtils.isAPushable(board[row + aHead][column])) {
                return 1;
            }
        }
        return 0;
    }

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
