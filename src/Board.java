
import java.awt.Point;
import java.util.LinkedList;


public class Board {

	//ATTRIBUTES-------------------------------------------------------------------------

	//Numbers associated with each column
	public static final int A = 0;	//Leftmost column
	public static final int B = 1;	
	public static final int C = 2;	
	public static final int D = 3;	
	public static final int E = 4;	
	public static final int F = 5;	
	public static final int G = 6;	
	public static final int H = 7;	//Rightmost column

	//Numbers associated with each row
	public static final int L1 = 0;	//Bottom line
	public static final int L2 = 1;	
	public static final int L3 = 2;	
	public static final int L4 = 3;	
	public static final int L5 = 4;	
	public static final int L6 = 5;	
	public static final int L7 = 6;	
	public static final int L8 = 7;	//Top line

	//Number of squares per row or column
	public static final int MAX = 8;
	
	//The board itself
	private Square[][] board;
	
	//The updated positions of every black pushers
	private LinkedList<Point> listBPushers;
	//The updated positions of every white pushers
	private LinkedList<Point> listWPushers;
	
	//Number of black pushable on the board
	private int nbBPushables;
	//Number of white pushable on the board
	private int nbWPushables;

	//CONSTRUCTORS-----------------------------------------------------------------------

	/**
	 * Creates a board with the default arrangement
	 */
	public Board(){

		board = new Square[MAX][MAX];
		listBPushers = new LinkedList<Point>();
		listWPushers = new LinkedList<Point>();
		nbBPushables = 8;
		nbWPushables = 8;

		for(int i = A; i <= H; i++){
			board[i][L1] = new Square(Square.W_PUSHER);
			listWPushers.add(new Point(i,L1));
			board[i][L2] = new Square(Square.W_PUSHABLE);
			for(int j = L3; j <= L6; j++){
				board[i][j] = new Square(Square.EMPTY);
			}
			board[i][L7] = new Square(Square.B_PUSHABLE);
			board[i][L8] = new Square(Square.B_PUSHER);
			listBPushers.add(new Point(i,L8));
		}
	}

	/**
	 * Creates a board in a requested arrangement
	 * 
	 * @param board	Array of integers, representing the state of each square
	 */
	public Board(int[][] board){

		this.board = new Square[MAX][MAX];
		listBPushers = new LinkedList<Point>();
		listWPushers = new LinkedList<Point>();
		

		for(int i = A; i <= H; i++){
			
			for(int j = L1; j <= L8; j++){
				
				this.board[i][j].setState(board[i][j]);
				
				if(this.board[i][j].isAPusher()){
					
					if(this.board[i][j].isWhite()){
						listWPushers.add(new Point(i,L1));
					} else {
						listBPushers.add(new Point(i,L1));
					}
				} else if(this.board[i][j].isEmpty() == false){
					
					if(this.board[i][j].isWhite()){
						nbWPushables++;
					} else {
						nbBPushables++;
					}
				}
			}
		}
	}


	//GETTERS AND SETTERS----------------------------------------------------------------

	/**
	 * Gets the state of a square
	 * 
	 * @param letter	The first coordinate (int associated with the letter)
	 * @param number	The second coordinate (int associated with the number)
	 * @return	The integer value associated with the state of the square
	 */
	public int getSquareState(int letter, int number){
		return board[letter][number].getState();
	}


	/**
	 * Sets the state of a square
	 * 
	 * @param letter	The first coordinate (int associated with the letter)
	 * @param number	The second coordinate (int associated with the number)
	 * @param newState	The int value associated with desired state of the square
	 */
	public void setSquareState(int letter, int number, int newState){
		board[letter][number].setState(newState);
	}
	
	/**
	 * Returns the number of black pushers on the board
	 * 
	 * @return	The number of black pushers
	 */
	public int getNbBPushers(){
		return listBPushers.size();
	}
	
	/**
	 * Returns the number of white pushers on the board
	 * 
	 * @return	The number of white pushers
	 */
	public int getNbWPushers(){
		return listWPushers.size();
	}
	
	/**
	 * Returns the number of black pushable on the board
	 * 
	 * @return	The number of black pushable
	 */
	public int getNbBPushables(){
		return nbBPushables;
	}
	
	/**
	 * Returns the number of white pushable on the board
	 * 
	 * @return	The number of white pushable
	 */
	public int getNbWPushables(){
		return nbWPushables;
	}
	
	/**
	 * Returns a copy of the list containing the positions of every black pushers 
	 * 
	 * @return	A copy of the list
	 */
	public LinkedList<Point> getListBPushers(){
		return listBPushers;
	}
	
	/**
	 * Returns a copy of the list containing the positions of every white pushers 
	 * 
	 * @return	A copy of the list
	 */
	public LinkedList<Point> getListWPushers(){
		return listWPushers;
	}
	
	
	//METHODS----------------------------------------------------------------------------
	
	/**
	 * Moves a piece from one square to another
	 * 
	 * @param fromLetter	first coordinate of the starting square
	 * @param fromNumber	second coordinate of the starting square
	 * @param toLetter	first coordinate of the destination square
	 * @param toNumber	second coordinate of the destination square
	 */
	public void movePiece(int fromLetter, int fromNumber, int toLetter, int toNumber){
		
		//Gets the state of the square
		int state = board[fromLetter][fromNumber].getState();
		
		//If there is a piece on the starting square
		if (state != Square.EMPTY){
			
			//Empties the starting square
			removePiece(fromLetter, fromNumber);
				
			//Deletes the piece on the destination square, if there's one
			removePiece(toLetter, toNumber);
				
			//Puts the piece on its new square
			addPiece(state, toLetter, toNumber);
			
			//Updates the position of the pusher in the appropriate list
		}
		
	}
	

	
	/**
	 * Removes a piece and decreases the number of said pieces on the board
	 * 
	 * @param state		Integer representation of the piece to be added
	 * @param letter	First coordinate
	 * @param number	Second coordinate
	 */
	private void addPiece(int state, int letter, int number){

		//The current square being filled
		Square square = board[letter][number];
		//The position of the square
		Point point = new Point(letter,number);

		//Update of the correct list if the new piece is a pusher

		if (state == Square.W_PUSHER){
			listWPushers.add(point);
		}

		if (state == Square.B_PUSHER){
			listBPushers.add(point);
		}

		//If the added piece is a pushable

		if (state == Square.W_PUSHABLE){
			nbWPushables++;
		}

		if (state == Square.B_PUSHABLE){
			nbBPushables++;
		}

		//Once the number is increased, the actual addition occurs
		square.setState(state);
	}
	
	
	
	/**
	 * Removes a piece and decreases the number of said pieces on the board
	 * 
	 * @param letter	First coordinate
	 * @param number	Second coordinate
	 */
	private void removePiece(int letter, int number){
		
		//The current square being emptied
		Square square = board[letter][number];
		//The position of the square
		Point point = new Point(letter,number);
		
		//If the square isn't already empty
		if (square.isEmpty() == false){
			
			//If the square contains a pusher
			if (square.isAPusher()){
				
				if (square.isWhite()){
					listWPushers.remove(point);
				} else {
					listBPushers.remove(point);
				}
				
			//If the square contains a pushable	
			} else {
				
				if (square.isWhite()){
					nbWPushables--;
				} else {
					nbBPushables--;
				}
				
			}
			
			//Once the number is decreased, the actual deletion occurs
			square.setState(Square.EMPTY);
			
		}
			
		
	}

	
	/**
	 * Prints the board on the console
	 */
	public void printBoard(){

		//Identifies the columns
		System.out.println("  A B C D E F G H");

		for(int i = L8; i >= L1; i--){

			//Identifies the lines
			System.out.print(i+1);

			for(int j = A; j <= H; j++){
				System.out.print(" " + board[j][i].toString());
			}

			//Identifies the lines
			System.out.println(" " + (i+1));
		}

		//Identifies the columns
		System.out.println("  A B C D E F G H");
	}
	
	public String toString(){

		//Identifies the columns
		String str = new String("  A B C D E F G H" + System.getProperty("line.separator"));

		for(int i = L8; i >= L1; i--){

			//Identifies the lines
			str = str.concat(Integer.toString(i+1));

			for(int j = A; j <= H; j++){
				str = str.concat(" " + board[j][i].toString());
			}

			//Identifies the lines
			str = str.concat(" " + Integer.toString(i+1) + System.getProperty("line.separator"));
		}

		//Identifies the columns
		str = str.concat("  A B C D E F G H");
		
		
		
		return str;
	}
}
