package com.Pushers.Main;

import com.Pushers.Bean.Board;
import com.Pushers.Bean.Move;
import com.Pushers.Bean.Player;
import com.Pushers.Utils.MoveUtils;

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
	List<Move> moveList;
	
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
				board = new Board(size, aBuffer);
				player = new Player(board, true);

                Move move = player.getMoveFromMinMax();

                String moveString = move.toString();
				output.write(moveString.getBytes(),0,moveString.length());
				output.flush();

				board.movePiece(move);
            }

            if(cmd == '2'){
                System.out.println("Nouvelle partie! Vous jouer noir, attendez le coup des blancs");
                byte[] aBuffer = new byte[1024];
				int size = input.available();
				input.read(aBuffer,0,size);
				board = new Board(size, aBuffer);
				player = new Player(board, false);
            }


			// Le serveur demande le prochain coup
			// Le message contient aussi le dernier coup joue.
			if(cmd == '3'){
				byte[] aBuffer = new byte[16];
				int size = input.available();
				input.read(aBuffer,0,size);

				String moveStringServer = new String(aBuffer);
				System.out.println("Dernier coup : "+ moveStringServer);

                Move serverMove = new Move(MoveUtils.getRowNumber(Integer.valueOf(moveStringServer.substring(2,3))),
                                           MoveUtils.getColumnNumber(moveStringServer.charAt(1)),
                                           MoveUtils.getRowNumber(Integer.valueOf(moveStringServer.substring(7,8))),
                                           MoveUtils.getColumnNumber(moveStringServer.charAt(6)));
                board.movePiece(serverMove);


                System.out.println("Entrez votre coup : ");
                Move move = player.getMoveFromMinMax();
				String moveString = move.toString();
				output.write(moveString.getBytes(), 0, moveString.length());
				output.flush();

				board.movePiece(move);
			}
			// Le dernier coup est invalide
			if(cmd == '4'){
                /*
				System.out.println("Coup invalide, entrez un nouveau coup : ");
				
				moveList = player.getAllMoves();
				int random = (int)(Math.random() * moveList.size());
				
		       	String move = moveList.get(random).toString();
				output.write(move.getBytes(),0,move.length());
				output.flush();
				
				int fromRow = moveList.get(random).getFromRow();
				int fromColumn = moveList.get(random).getFromColumn();
				int toRow = moveList.get(random).getToRow();
				int toColumn = moveList.get(random).getToColumn();
				board.movePiece(moveList.get(random));
				*/
			}
			
			board.printBoard();
        }
	}
	catch (IOException e) {
   		System.out.println(e);
	}
	
    }
}
