import java.awt.Point;
import java.util.LinkedList;


public class test {
	
	//Numbers associated with each column
		public static final int A = Board.A;	//Leftmost column
		public static final int B = Board.B;	
		public static final int C = Board.C;	
		public static final int D = Board.D;	
		public static final int E = Board.E;	
		public static final int F = Board.F;	
		public static final int G = Board.G;	
		public static final int H = Board.H;	//Rightmost column

		//Numbers associated with each row
		public static final int L1 = Board.L1;	//Bottom line
		public static final int L2 = Board.L2;	
		public static final int L3 = Board.L3;	
		public static final int L4 = Board.L4;	
		public static final int L5 = Board.L5;	
		public static final int L6 = Board.L6;	
		public static final int L7 = Board.L7;	
		public static final int L8 = Board.L8;	//Top line

	public static void main(String[] args) {
		
		Board board = new Board();

		
		System.out.println(board);;

		System.out.println();
		System.out.print("X: " + board.getNbBPushers() + ", ");
		System.out.print("x: " + board.getNbBPushables() + ", ");
		System.out.print("o: " + board.getNbWPushables() + ", ");
		System.out.println("O: " + board.getNbWPushers());
		
		Player player = new Player(board, false);
		
		LinkedList<Move> mList = player.getAllMoves();
		
		
		int startingSize = mList.size();
		
		while(mList.isEmpty() == false){

			if((startingSize - mList.size()) % 10 == 0)
				System.out.println("");
			
			System.out.print(mList.poll() + ", ");
			
		}
		

	}

}
