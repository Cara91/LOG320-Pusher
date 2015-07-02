import java.awt.Point;
import java.util.LinkedList;


/*public class test {
	
	//Numbers associated with each column
		public static final int A = com.Pushers.Bean.Board.COLUMN_A;	//Leftmost column
		public static final int B = com.Pushers.Bean.Board.COLUMN_B;
		public static final int C = com.Pushers.Bean.Board.COLUMN_C;
		public static final int D = com.Pushers.Bean.Board.COLUMN_D;
		public static final int E = com.Pushers.Bean.Board.COLUMN_E;
		public static final int F = com.Pushers.Bean.Board.COLUMN_F;
		public static final int G = com.Pushers.Bean.Board.COLUMN_G;
		public static final int H = com.Pushers.Bean.Board.COLUMN_H;	//Rightmost column

		//Numbers associated with each row
		public static final int L1 = com.Pushers.Bean.Board.ROW_1;	//Bottom line
		public static final int L2 = com.Pushers.Bean.Board.ROW_2;
		public static final int L3 = com.Pushers.Bean.Board.ROW_3;
		public static final int L4 = com.Pushers.Bean.Board.ROW_4;
		public static final int L5 = com.Pushers.Bean.Board.ROW_5;
		public static final int L6 = com.Pushers.Bean.Board.ROW_6;
		public static final int L7 = com.Pushers.Bean.Board.ROW_7;
		public static final int L8 = com.Pushers.Bean.Board.ROW_8;	//Top line

	public static void main(String[] args) {
		
		com.Pushers.Bean.Board board = new com.Pushers.Bean.Board();

		
		System.out.println(board);;

		System.out.println();
		System.out.print("X: " + board.getNbBPushers() + ", ");
		System.out.print("x: " + board.getNbBPushables() + ", ");
		System.out.print("o: " + board.getNbWPushables() + ", ");
		System.out.println("O: " + board.getNbWPushers());
		
		com.Pushers.Bean.Player player = new com.Pushers.Bean.Player(board, false);
		
		LinkedList<com.Pushers.Bean.Move> mList = player.getAllMoves();
		
		
		int startingSize = mList.size();
		
		while(mList.isEmpty() == false){

			if((startingSize - mList.size()) % 10 == 0)
				System.out.println("");
			
			System.out.print(mList.poll() + ", ");
			
		}
		

	}

}*/
