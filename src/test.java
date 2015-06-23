import java.awt.Point;
import java.util.LinkedList;


/*public class test {
	
	//Numbers associated with each column
		public static final int A = Board.COLUMN_A;	//Leftmost column
		public static final int B = Board.COLUMN_B;	
		public static final int C = Board.COLUMN_C;	
		public static final int D = Board.COLUMN_D;	
		public static final int E = Board.COLUMN_E;	
		public static final int F = Board.COLUMN_F;	
		public static final int G = Board.COLUMN_G;	
		public static final int H = Board.COLUMN_H;	//Rightmost column

		//Numbers associated with each row
		public static final int L1 = Board.ROW_1;	//Bottom line
		public static final int L2 = Board.ROW_2;	
		public static final int L3 = Board.ROW_3;	
		public static final int L4 = Board.ROW_4;	
		public static final int L5 = Board.ROW_5;	
		public static final int L6 = Board.ROW_6;	
		public static final int L7 = Board.ROW_7;	
		public static final int L8 = Board.ROW_8;	//Top line

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

}*/
