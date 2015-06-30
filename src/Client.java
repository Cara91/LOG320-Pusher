import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


class Client {
	public static void main(String[] args) {
         
	Socket MyClient;
	BufferedInputStream input;
	BufferedOutputStream output;
    int[][] boardTable = new int[8][8];
    
    Player player = null;
	Board board = null;
	List<Move> mList;
	
	try {
		MyClient = new Socket("localhost", 8888);
	   	input    = new BufferedInputStream(MyClient.getInputStream());
		output   = new BufferedOutputStream(MyClient.getOutputStream());
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));  
				
	   	while(1 == 1){
			char cmd = 0;
		   	
            cmd = (char)input.read();
            		
            // Debut de la partie en joueur blanc
            if(cmd == '1'){
                byte[] aBuffer = new byte[1024];
				
				int size = input.available();
				input.read(aBuffer,0,size);
				
				int column = 0;
				int row = 7;
				for (int i = 1; i < size; i+=2) {
					boardTable[row][column] = (int) aBuffer[i] - 48;
					
					column++;
					if(column == 8){
						column = 0;
						row--;
					}
				}

				board = new Board(boardTable);	
				player = new Player(board, true);			
				mList = player.getAllMoves();
				
				for (int i = 0; i < mList.size(); i++) {

					if(i % 10 == 0)
						System.out.println("");
					
					System.out.print(mList.get(i) + ", ");
				}
				
				int random = (int)(Math.random() * mList.size());
				
                System.out.println("Nouvelle partie! Vous jouer blanc, entrez votre premier coup : ");
                System.out.println(mList.get(random).toString());
                String move = mList.get(random).toString();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
				int fromRow = mList.get(random).getFromRow();
				int fromColumn = mList.get(random).getFromColumn();
				int toRow = mList.get(random).getToRow();
				int toColumn = mList.get(random).getToColumn();
				board.movePiece(fromRow, fromColumn, toRow, toColumn);
            }
            
            // Debut de la partie en joueur Noir
            if(cmd == '2'){
                System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
                byte[] aBuffer = new byte[1024];
				
				int size = input.available();
				input.read(aBuffer,0,size);
				
				int column = 0;
				int row = 7;
				for (int i = 1; i < size; i+=2) {
					boardTable[row][column] = (int) aBuffer[i] - 48;
					
					column++;
					if(column == 8){
						column = 0;
						row--;
					}
				}
				
				board = new Board(boardTable);
				
				player = new Player(board, false);
				mList = player.getAllMoves();
				
				for (int i = 0; i < mList.size(); i++) {

					if(i % 10 == 0)
						System.out.println("");
					
					System.out.print(mList.get(i) + ", ");
				}
            }


			// Le serveur demande le prochain coup
			// Le message contient aussi le dernier coup joue.
			if(cmd == '3'){
				byte[] aBuffer = new byte[16];
				
				int size = input.available();
				input.read(aBuffer,0,size);
				
				mList = player.getAllMoves();
				
				for (int i = 0; i < mList.size(); i++) {

					if(i % 10 == 0)
						System.out.println("");
					
					System.out.print(mList.get(i) + ", ");
				}
				
				int random = (int)(Math.random() * mList.size());
				
				
				String s = new String(aBuffer);
				System.out.println("Dernier coup : "+ s);
		       	System.out.println("Entrez votre coup : ");
				String move = mList.get(random).toString();
				output.write(move.getBytes(),0,move.length());
				output.flush();

				Move serverMove = new Move(Integer.valueOf(s.substring(2,3)), s.charAt(1), Integer.valueOf(s.substring(7,8)), s.charAt(6));
				board.movePiece(serverMove.getFromRow(), serverMove.getFromColumn(), serverMove.getToRow(), serverMove.getToColumn());
				
				int fromRow = mList.get(random).getFromRow();
				int fromColumn = mList.get(random).getFromColumn();
				int toRow = mList.get(random).getToRow();
				int toColumn = mList.get(random).getToColumn();
				board.movePiece(fromRow, fromColumn, toRow, toColumn);
			}
			// Le dernier coup est invalide
			if(cmd == '4'){
				System.out.println("Coup invalide, entrez un nouveau coup : ");
				
				mList = player.getAllMoves();
				int random = (int)(Math.random() * mList.size());
				
		       	String move = mList.get(random).toString();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
				int fromRow = mList.get(random).getFromRow();
				int fromColumn = mList.get(random).getFromColumn();
				int toRow = mList.get(random).getToRow();
				int toColumn = mList.get(random).getToColumn();
				board.movePiece(fromRow, fromColumn, toRow, toColumn);
			}
			
			board.printBoard();
        }
	}
	catch (IOException e) {
   		System.out.println(e);
	}
	
    }
}
