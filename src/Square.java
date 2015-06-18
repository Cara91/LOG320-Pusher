
/**
 * @author Anthony Garneau
 *
 */
public class Square {

	//ATTRIBUTES
	
	//Numbers representing what is on the cell
	public static final int B_PUSHER = -2;
	public static final int B_PUSHABLE = -1;
	public static final int EMPTY = 0;
	public static final int W_PUSHABLE = 1;
	public static final int W_PUSHER = 2;
	
	//Characters to be displayed when printing each piece type
	public static final String DISP_B_PUSHER = "X";
	public static final String DISP_B_PUSHABLE = "x";
	public static final String DISP_EMPTY = "-";
	public static final String DISP_W_PUSHABLE = "o";
	public static final String DISP_W_PUSHER = "O";
	//State of the square (contains a black pusher, is empty, etc.)
	private int state;
	
	
	//CONSTRUCTOR
	public Square(int state){
		this.state = state;
	}

	
	//GETTERS AND SETTERS
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Check the square and returns true if it contains a pusher
	 * 
	 * @return	true if the square contains a pusher, false otherwise
	 */
	public boolean isAPusher(){
		if(state == B_PUSHER || state == W_PUSHER)
			return true;
		return false;
	}

	/**
	 * Check the square and returns true if it contains a white piece
	 * 
	 * @return	true if the square contains a white piece, false otherwise
	 */
	public boolean isWhite(){
		if(state == W_PUSHER || state == W_PUSHABLE)
			return true;
		return false;
	}

	/**
	 * Check the square and returns true if it is empty
	 * 
	 * @return	true if the square is empty, false otherwise
	 */
	public boolean isEmpty(){
		if(state == EMPTY)
			return true;
		return false;
	}
	
	
	public String toString(){
		switch(state){
		case B_PUSHER: return DISP_B_PUSHER;
		case B_PUSHABLE: return DISP_B_PUSHABLE;
		case W_PUSHABLE: return DISP_W_PUSHABLE;
		case W_PUSHER: return DISP_W_PUSHER;
		default: return DISP_EMPTY;
		}
	}
}
