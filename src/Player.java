import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.Pushers.Utils.BoardUtil;


public class Player {

	//ATTRIBUTES

	public static final int LEFT = -1;
	public static final int CENTER = 0;
	public static final int RIGHT = 1;


	//The board itself
	private Board board;
	//True if the player is playing with the white team, false if not
	private boolean isWhite;
	//Updated list of the positions of the player's pushers
	private List<Point> pusherList;


	//CONSTRUCTOR

	public Player(Board board, boolean isWhite){

		this.board = board;
		this.isWhite = isWhite;

		if (isWhite){
			pusherList = board.getListWPushers(); 
		} else {
			pusherList = board.getListBPushers();
		}
	}

	/**
	 * Creates a list of all the moves the player could do in a turn
	 * 
	 * @return a list of all the possible moves
	 */
	public List<Move> getAllMoves(){

		//The list of all the moves
		List<Move> moveList = new ArrayList<Move>();

		//The list of all the pushers, which are the only one creating motion
		List<Point> pusherList = this.pusherList;

		for (int i = 0; i < pusherList.size(); i++) {

			/* Creates the table that will contain all the moves for that pusher
			 * There is a maximum of three moves a pusher can do, either it moves N, NW or NE,
			 * or it pushes a pushable piece N, NW or NE (S, SW or SE for black pieces).
			 * The table can only contain a maximum of three moves for that reason.
			 */
			Move[] moveTab = new Move[3];

			//Gets the position of the pusher
			Point point = pusherList.get(i);

			//Gets an array of moves for that particular pusher
			moveTab = getMoves(point.x,point.y);

			//For every moves the pusher can do
			for (int j = 0; j < moveTab.length; j++){

				//If the move exists
				if (moveTab[j] != null){

					//Adds the move to the list of all moves possible
					moveList.add(moveTab[j]);
				}
			}
		}

		return moveList;
	}

	private Move[] getMoves(int row, int column){

		Move[] moveTab = new Move[3];

		moveTab[0] = lookAhead(row, column, LEFT);
		moveTab[1] = lookAhead(row, column, CENTER);
		moveTab[2] = lookAhead(row, column, RIGHT);

		return moveTab;
	}


	/**
	 * Verifies if the pusher on the square can make a move (move or push) towards the left side of the board.
	 * 
	 * @param row	The first coordinate of the square
	 * @param column	The second coordinate of the square
	 * @param dirLR		Used to know if the pusher is going left or right (it represents one step in said direction)
	 * @return	The move that can be made, or null if no move can be made
	 */
	private Move lookAhead(int row, int column, int dirLR){

		//The possible move in the chosen direction
		Move move = null;

		//Used to know if the pusher is going up or down
		int dirUD;

		if (isWhite)
			dirUD = 1;	//White pieces go upward, represents one step upward
		else
			dirUD = -1;	//Black pieces go downward, represents one step downward

		//Verifies if the pusher is going to fall off the board
		if ((column != Board.COLUMN_A || dirLR != LEFT) && (column != Board.COLUMN_H || dirLR != RIGHT)){
			
			//Evaluates the next square in the chosen direction
			switch (board.getSquareState(row+dirUD, column+dirLR)){

			case BoardUtil.W_PUSHER:
				
				//To take a white pusher, the player needs to be black and needs to be going diagonally
				if(isWhite == false && dirLR != CENTER){
					//Creates the move to take the white pusher
					move = new Move(row,column,row+dirUD,column+dirLR);
				}
				
				break;
				
			case BoardUtil.B_PUSHER:
				
				//To take a black pusher, the player needs to be white and needs to be going diagonally
				if(isWhite == true && dirLR != CENTER){
					//Creates the move to take the black pusher
					move = new Move(row,column,row+dirUD,column+dirLR);
				}
				
				break;
				
			case BoardUtil.W_PUSHABLE:
				
				/* To take a white pushable, a pusher must meet those rules:
				 * 1) The pusher must be black
				 * 2) The pusher must be moving diagonally
				 */
				if((isWhite == false) && (dirLR != CENTER)){
					
					move = new Move(row,column,row+dirUD,column+dirLR);
					
					/*	To be allowed to push a white pushable, a pusher must meet those rules:
					 * 1) The pusher must be white
					 * 2) The pushable must not fall off the board if it is pushed
					 * 3) The pushable can't be pushed on a white piece
					 * 4) The square beyond the pushable must be either empty or on a diagonal (Pieces can't eat on a straight line) 
					 */
				} else if((isWhite == true) &&	// 1)
						  (column+dirLR != Board.COLUMN_A || dirLR != LEFT) && (column+dirLR != Board.COLUMN_H || dirLR != RIGHT) &&	// 2)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtil.W_PUSHER) &&	// 3)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtil.W_PUSHABLE) &&	// 3)
						  ((board.getSquareState(row+(2*dirUD),column+(2*dirLR)) == BoardUtil.EMPTY) || (dirLR != CENTER))){	// 4)
					
					//Moves the adjacent pushable piece one square in the same direction
					move = new Move(row+dirUD,column+dirLR,row+(2*dirUD),column+(2*dirLR));	
				}
				
				break;
				
			case BoardUtil.B_PUSHABLE:
				
				/* To take a black pushable, a pusher must meet those rules:
				 * 1) The pusher must be white
				 * 2) The pusher must be moving diagonally
				 */
				if((isWhite == true) && (dirLR != CENTER)){
					
					move = new Move(row,column,row+dirUD,column+dirLR);
					
					/*	To be allowed to push a black pushable, a pusher must meet those rules:
					 * 1) The pusher must be black
					 * 2) The pushable must not fall off the board if it is pushed
					 * 3) The pushable can't be pushed on a black piece
					 * 4) The square beyond the pushable must be either empty or on a diagonal (Pieces can't eat on a straight line) 
					 */
				} else if((isWhite == false) &&	// 1)
						  (column+dirLR != Board.COLUMN_A || dirLR != LEFT) && (column+dirLR != Board.COLUMN_H || dirLR != RIGHT) &&	// 2)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtil.B_PUSHER) &&	// 3)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtil.B_PUSHABLE) &&	// 3)
						  ((board.getSquareState(row+(2*dirUD),column+(2*dirLR)) == BoardUtil.EMPTY) || (dirLR != CENTER))){	// 4)
					
					//Moves the adjacent pushable piece one square in the same direction
					move = new Move(row+dirUD,column+dirLR,row+(2*dirUD),column+(2*dirLR));	
				}
				
				break;
				
			default:
				//If the space is empty, the player can move there
				move = new Move(row,column,row+dirUD,column+dirLR);

			}
		}

		return move;
	}
}