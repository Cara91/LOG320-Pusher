
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.Pushers.Utils.BoardUtil;


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
	
	private int[][] board;
	
	private List<Point> listBlackPushers;
	private List<Point> listWhitePushers;
	
	private int nbBlackPushables;
	private int nbWhitePushables;

	/**
	 * Creates a board in a requested arrangement
	 * 
	 * @param board	Array of integers, representing the state of each square
	 */
	public Board(int[][] board){

		this.board = board;
		
		listBlackPushers = new ArrayList<Point>(8);
		listWhitePushers = new ArrayList<Point>(8);
		
		setListPusher();
	}

	private void setListPusher() {
		for(int i = COLUMN_A; i <= COLUMN_H; i++){
			
			for(int j = ROW_1; j <= ROW_8; j++){

				if(BoardUtil.isAPusher(this.board[i][j])){
					
					if(BoardUtil.isWhite(this.board[i][j])){
						listWhitePushers.add(new Point(i, j));
					} else {
						listBlackPushers.add(new Point(i, j));
					}
				} else if(BoardUtil.isEmpty(this.board[i][j]) == false){
					
					if(BoardUtil.isWhite(this.board[i][j])){
						nbWhitePushables++;
					} else {
						nbBlackPushables++;
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
	 * Returns the number of black pushable on the board
	 * 
	 * @return	The number of black pushable
	 */
	public int getNbBPushables(){
		return nbBlackPushables;
	}
	
	/**
	 * Returns the number of white pushable on the board
	 * 
	 * @return	The number of white pushable
	 */
	public int getNbWPushables(){
		return nbWhitePushables;
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
	
	public void movePiece(int fromRow, int fromColumn, int toRow, int toColumn){
		//Updates the position of the pusher in the appropriate list
		int state = board[fromRow][fromColumn];
		
		if (state != BoardUtil.EMPTY){		
			removePiece(fromRow, fromColumn);
				
			removePiece(toRow, toColumn);

			addPiece(state, toRow, toColumn);
		}	
	}
	
	private void addPiece(int state, int row, int column){

		//The current square being filled
		board[row][column] = state;
		//The position of the square
		Point point = new Point(row, column);

		//Update of the correct list if the new piece is a pusher

		if (state == BoardUtil.W_PUSHER){
			listWhitePushers.add(point);
		}

		if (state == BoardUtil.B_PUSHER){
			listBlackPushers.add(point);
		}

		//If the added piece is a pushable

		if (state == BoardUtil.W_PUSHABLE){
			nbWhitePushables++;
		}

		if (state == BoardUtil.B_PUSHABLE){
			nbBlackPushables++;
		}
	}
	
	private void removePiece(int row, int column){
		
		//The current square being emptied
		int square = board[row][column];
		//The position of the square
		Point point = new Point(row, column);
		
		//If the square isn't already empty
		if (BoardUtil.isEmpty(square) == false){
			
			//If the square contains a pusher
			if (BoardUtil.isAPusher(square)){
				
				if (BoardUtil.isWhite(square)){
					listWhitePushers.remove(point);
				} else {
					listBlackPushers.remove(point);
				}
				
			//If the square contains a pushable	
			} else {
				
				if (BoardUtil.isWhite(square)){
					nbWhitePushables--;
				} else {
					nbBlackPushables--;
				}
				
			}
		
			board[row][column] = BoardUtil.EMPTY;		
		}		
	}

	public void printBoard(){

		//Identifies the columns
		System.out.println("  A B C D E F G H");

		for(int i = ROW_8; i >= ROW_1; i--){

			//Identifies the lines
			System.out.print(i+1);

			for(int j = COLUMN_A; j <= COLUMN_H; j++){
				System.out.print(" " + BoardUtil.stateToString(board[i][j]));
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
}
