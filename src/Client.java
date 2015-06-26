import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


class Client {
	public static void main(String[] args) {
         
	Socket MyClient;
	BufferedInputStream input;
	BufferedOutputStream output;
    int[][] board = new int[8][8];
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
				//System.out.println("size " + size);
				input.read(aBuffer,0,size);
				
				int column = 0;
				int row = 7;
				for (int i = 1; i < size; i+=2) {
					board[row][column] = (int) aBuffer[i] - 48;
					
					column++;
					if(column == 8){
						column = 0;
						row--;
					}
				}

                System.out.println("Nouvelle partie! Vous jouer blanc, entrez votre premier coup : ");
                String move = null;
                move = console.readLine();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
				Board board2 = new Board(board);
				board2.printBoard();
				
				Player player = new Player(board2, true);
				List<Move> mList = player.getAllMoves();
				
				for (int i = 0; i < mList.size(); i++) {

					if(i % 10 == 0)
						System.out.println("");
					
					System.out.print(mList.get(i) + ", ");
				}
				
            }
            // Debut de la partie en joueur Noir
            if(cmd == '2'){
                System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
                byte[] aBuffer = new byte[1024];
				
				int size = input.available();
				//System.out.println("size " + size);
				input.read(aBuffer,0,size);
				
				int column = 0;
				int row = 7;
				for (int i = 1; i < size; i+=2) {
					board[row][column] = (int) aBuffer[i] - 48;
					
					column++;
					if(column == 8){
						column = 0;
						row--;
					}
				}
				
				Board board2 = new Board(board);
				board2.printBoard();
				
				Player player = new Player(board2, false);
				List<Move> mList = player.getAllMoves();
				
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
				//System.out.println("size " + size);
				input.read(aBuffer,0,size);
				
				String s = new String(aBuffer);
				System.out.println("Dernier coup : "+ s);
		       	System.out.println("Entrez votre coup : ");
				String move = null;
				move = console.readLine();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
			}
			// Le dernier coup est invalide
			if(cmd == '4'){
				System.out.println("Coup invalide, entrez un nouveau coup : ");
		       	String move = null;
				move = console.readLine();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
			}
        }
	}
	catch (IOException e) {
   		System.out.println(e);
	}
	
    }
}
