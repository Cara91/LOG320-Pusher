package com.Pushers.Bean;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.Pushers.Utils.BoardUtils;
import com.Pushers.Utils.HeuristicUtils;


public class Board {

	public static final int COLUMN_A = 0;
	public static final int COLUMN_B = 1;	
	public static final int COLUMN_C = 2;	
	public static final int COLUMN_D = 3;	
	public static final int COLUMN_E = 4;	
	public static final int COLUMN_F = 5;	
	public static final int COLUMN_G = 6;	
	public static final int COLUMN_H = 7;

	public static final int ROW_1 = 0;
	public static final int ROW_2 = 1;	
	public static final int ROW_3 = 2;	
	public static final int ROW_4 = 3;	
	public static final int ROW_5 = 4;	
	public static final int ROW_6 = 5;	
	public static final int ROW_7 = 6;	
	public static final int ROW_8 = 7;

	public static final int MAX_SQUARE = 8;
	
	private int[][] board = new int[8][8];
	
	private List<Point> listBlackPushers;
	private List<Point> listWhitePushers;
    private List<Point> listBlackPushables;
    private List<Point> listWhitePushables;
	
	//private int nbBlackPushables;
	//private int nbWhitePushables;

    public Board(){
    }

    public Board(int size, byte[] byteTable){
        int column = 0;
        int row = 7;
        for (int i = 1; i < size; i+=2) {
            this.board[row][column] = (int) byteTable[i] - 48;

            column++;
            if(column == 8){
                column = 0;
                row--;
            }
        }

        listBlackPushers = new ArrayList<Point>(8);
        listWhitePushers = new ArrayList<Point>(8);
        listBlackPushables = new ArrayList<Point>(8);
        listWhitePushables = new ArrayList<Point>(8);

        setListPusher();
    }

	private void setListPusher() {
		for(int i = COLUMN_A; i <= COLUMN_H; i++){
			
			for(int j = ROW_1; j <= ROW_8; j++){

				if(BoardUtils.isAPusher(this.board[i][j])){
					
					if(BoardUtils.isWhite(this.board[i][j])){
						listWhitePushers.add(new Point(i, j));
					} else {
						listBlackPushers.add(new Point(i, j));
					}
				} else if(BoardUtils.isEmpty(this.board[i][j]) == false){
					
					if(BoardUtils.isWhite(this.board[i][j])){
                        listWhitePushables.add(new Point(i, j));
						//nbWhitePushables++;
					} else {
                        listBlackPushables.add(new Point(i, j));
						//nbBlackPushables++;
					}
				}
			}
		}
	}

	public int getSquareState(int row, int column){
		return board[row][column];
	}

	public void setSquareState(int row, int column, int newState){
		board[row][column] = newState;
	}
	
	/**
	 * Returns the number of black pushers on the board
	 * 
	 * @return	The number of black pushers
	 */
	public int getNbBPushers(){
		return listBlackPushers.size();
	}
	
	/**
	 * Returns the number of white pushers on the board
	 * 
	 * @return	The number of white pushers
	 */
	public int getNbWPushers(){
		return listWhitePushers.size();
	}
	
	/**
	 * Returns a copy of the list containing the positions of every black pushers 
	 * 
	 * @return	A copy of the list
	 */
	public List<Point> getListBPushers(){
		return listBlackPushers;
	}
	
	/**
	 * Returns a copy of the list containing the positions of every white pushers 
	 * 
	 * @return	A copy of the list
	 */
	public List<Point> getListWPushers(){
		return listWhitePushers;
	}

    public void setBoard(int[][] board){
        this.board = board;
    }

    public int[][] getBoard(){
        return this.board;
    }

    public void undoMove(Move move, int stateFrom, int stateTo){

        removePiece(move.getToRow(), move.getToColumn());
        addPiece(stateFrom, move.getFromRow(), move.getFromColumn());
        if(stateTo != BoardUtils.EMPTY){
            addPiece(stateTo, move.getToRow(), move.getToColumn());
        }
    }

	public void movePiece(Move move){
		int state = board[move.getFromRow()][move.getFromColumn()];

        removePiece(move.getFromRow(), move.getFromColumn());
        removePiece(move.getToRow(), move.getToColumn());
        addPiece(state, move.getToRow(), move.getToColumn());
	}
	
	private void addPiece(int state, int row, int column){

		//The current square being filled
		board[row][column] = state;
		//The position of the square
		Point point = new Point(row, column);

		//Update of the correct list if the new piece is a pusher

		if (state == BoardUtils.W_PUSHER){
			listWhitePushers.add(point);
		}

		if (state == BoardUtils.B_PUSHER){
			listBlackPushers.add(point);
		}

		//If the added piece is a pushable

		if (state == BoardUtils.W_PUSHABLE){
            listWhitePushables.add(point);
			//nbWhitePushables++;
		}

		if (state == BoardUtils.B_PUSHABLE){
            listBlackPushables.add(point);
			//nbBlackPushables++;
		}
	}
	
	private void removePiece(int row, int column){
		
		//The current square being emptied
		int square = board[row][column];
		//The position of the square
		Point point = new Point(row, column);
		
		//If the square isn't already empty
		if (BoardUtils.isEmpty(square) == false){
			
			//If the square contains a pusher
			if (BoardUtils.isAPusher(square)){
				
				if (BoardUtils.isWhite(square)){
					listWhitePushers.remove(point);
				} else {
					listBlackPushers.remove(point);
				}
				
			//If the square contains a pushable	
			} else {
				
				if (BoardUtils.isWhite(square)){
					listWhitePushables.remove(point);
                    //nbWhitePushables--;
				} else {
                    listBlackPushables.remove(point);
					//nbBlackPushables--;
				}
				
			}
		
			board[row][column] = BoardUtils.EMPTY;
		}		
	}

	public void printBoard(){

		//Identifies the columns
		System.out.println("  A B C D E F G H");

		for(int i = ROW_8; i >= ROW_1; i--){

			//Identifies the lines
			System.out.print(i+1);

			for(int j = COLUMN_A; j <= COLUMN_H; j++){
				System.out.print(" " + BoardUtils.stateToString(board[i][j]));
			}

			//Identifies the lines
			System.out.println(" " + (i+1));
		}

		//Identifies the columns
		System.out.println("  A B C D E F G H");
		
		//Identifies the columns
				System.out.println("  A B C D E F G H");

				for(int i = ROW_8; i >= ROW_1; i--){

					//Identifies the lines
					System.out.print(i+1);

					for(int j = COLUMN_A; j <= COLUMN_H; j++){
						System.out.print(" " + board[i][j]);
					}

					//Identifies the lines
					System.out.println(" " + (i+1));
				}

				//Identifies the columns
				System.out.println("  A B C D E F G H");
	}

    public int calculateScore(boolean isWhite){
        int moveScore = 0;
        if(isWhite){
            moveScore += calculatePushableScore(this.listWhitePushables, true);
            moveScore += calculatePusherScore(this.listWhitePushers, true);
            moveScore -= calculatePushableScore(this.listBlackPushables, false);
            moveScore -= calculatePusherScore(this.listBlackPushers, false);
        }else{
            moveScore -= calculatePushableScore(this.listWhitePushables, true);
            moveScore -= calculatePusherScore(this.listWhitePushers, true);
            moveScore += calculatePushableScore(this.listBlackPushables, false);
            moveScore += calculatePusherScore(this.listBlackPushers, false);
        }
        return moveScore;
    }

    private int calculatePushableScore(List<Point> listPushable, boolean isWhite){
        int score = 0;
        for (int i = 0; i< listPushable.size(); i++){
            Point tempPoint = listPushable.get(i);
            score += HeuristicUtils.PUSHABLE_VALUE*HeuristicUtils.positionMultiplier(tempPoint.x, tempPoint.y, isWhite);
            score += HeuristicUtils.isBlocking(tempPoint.x, tempPoint.y, isWhite, this.board);
            score += HeuristicUtils.ableToMove(tempPoint.x, tempPoint.y, isWhite, this.board);
            score += HeuristicUtils.canEat(tempPoint.x, tempPoint.y, isWhite, this.board, false);
        }
        return score;
    }

    private int calculatePusherScore(List<Point> listPushable, boolean isWhite){
        int score=0;
        for (int i=0; i<listPushable.size(); i++ ){
            Point tempPoint = listPushable.get(i);
            score += HeuristicUtils.PUSHER_VALUE*HeuristicUtils.positionMultiplier(tempPoint.x, tempPoint.y, isWhite);
            score += HeuristicUtils.canEat(tempPoint.x, tempPoint.y, isWhite, this.board, true);
        }
        return score;
    }
}
