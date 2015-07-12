package com.Pushers.Bean;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.Pushers.Utils.BoardUtils;


public class Player {

	private Board board;
	private boolean isWhite;

	public Player(Board board, boolean isWhite){

		this.board = board;
		this.isWhite = isWhite;
	}

    public Move getMoveFromMinMax(){
        Move move = new Move(0,0,0,0);
        boolean max = true;
        return minMaxAlphaBeta(move, max, 0, -9999, 9999);
    }

    private Move minMaxAlphaBeta(Move move, boolean max, int depth, int alpha, int beta){
        if(depth == 4){
            move.setScore(board.calculateScore(this.isWhite));
            return move;
        }
        if(max){
            //System.out.println("Max");
            Move maxMove = new Move(0,0,0,0, -9999);
            List<Move> moveList = getAllMoves(this.isWhite);
            int actualAlpha = -9999;
            for (int i = 0; i < moveList.size(); i++){
                Move tempMove = moveList.get(i);
                int stateTo = this.board.getSquareState(tempMove.getToRow(), tempMove.getToColumn());
                int stateFrom = this.board.getSquareState(tempMove.getFromRow(), tempMove.getFromColumn());
                this.board.movePiece(tempMove);

                tempMove.setScore(minMaxAlphaBeta(moveList.get(i), false, depth + 1,Math.max(actualAlpha, alpha), beta).getScore());
                //System.out.println("max:"+maxMove.getScore()+" | current:"+minMove.getScore());
                if(maxMove.compareTo(tempMove) == -1){
                    //System.out.println("Changed Max to:"+tempMove.getScore());
                    actualAlpha = tempMove.getScore();
                    maxMove = tempMove;
                }

                this.board.undoMove(tempMove, stateFrom, stateTo);

                if(actualAlpha >= beta){
                    //System.out.println("actualAlpha:"+actualAlpha+" >= beta:"+beta);
                    return maxMove;
                }
            }
            return maxMove;
        }else {
            //System.out.println("Min");
            Move minMove = new Move(0,0,0,0, 9999);
            List<Move> moveList = getAllMoves(!this.isWhite);
            int actualBeta = 9999;
            for (int i = 0; i < moveList.size(); i++){
                Move tempMove = moveList.get(i);
                int stateTo = this.board.getSquareState(tempMove.getToRow(), tempMove.getToColumn());
                int stateFrom = this.board.getSquareState(tempMove.getFromRow(), tempMove.getFromColumn());
                this.board.movePiece(tempMove);

                tempMove.setScore(minMaxAlphaBeta(moveList.get(i), true, depth + 1,alpha, Math.min(actualBeta, beta)).getScore());
                //System.out.println("min:"+minMove.getScore()+" | current:"+maxMove.getScore());
                if(minMove.compareTo(tempMove) == 1){
                    //System.out.println("Changed Min to:"+tempMove.getScore());
                    actualBeta = tempMove.getScore();
                    minMove = tempMove;
                }

                this.board.undoMove(tempMove, stateFrom, stateTo);
                if(actualBeta<=alpha){
                    //System.out.println("actualBeta:"+actualBeta+" <= alpha:"+alpha);
                    return minMove;
                }
            }
            return minMove;
        }
    }

	public List<Move> getAllMoves(boolean isWhite){
		List<Move> moveList = new ArrayList<Move>();
        List<Point> pusherList;
		if(isWhite) {
            pusherList = board.getListWPushers();
        }else{
            pusherList = board.getListBPushers();
        }

		for (int i = 0; i < pusherList.size(); i++) {

			Move[] moveTab = new Move[3];
			Point point = pusherList.get(i);
			moveTab = getMoves(point.x,point.y, isWhite);

			for (int j = 0; j < moveTab.length; j++){
				if (moveTab[j] != null){
					moveList.add(moveTab[j]);
				}
			}
		}
		return moveList;
	}
    //checker move pour si on est au bout
	private Move[] getMoves(int row, int column, boolean isWhite){
        Move[] moveTab = new Move[3];
		moveTab[0] = lookAhead(row, column, Move.LEFT, isWhite);
		moveTab[1] = lookAhead(row, column, Move.CENTER, isWhite);
		moveTab[2] = lookAhead(row, column, Move.RIGHT, isWhite);
		return moveTab;
	}

	private Move lookAhead(int row, int column, int dirLR, boolean isWhite){
        Move move = null;
		int dirUD;

        if((row == 0 && !isWhite) || (row == 7 && isWhite)){
            return null;
        }

		if (isWhite)
			dirUD = 1;
		else
			dirUD = -1;

		if ((column != Board.COLUMN_A || dirLR != Move.LEFT) && (column != Board.COLUMN_H || dirLR != Move.RIGHT)){
			switch (board.getSquareState(row+dirUD, column+dirLR)){

			case BoardUtils.W_PUSHER:
				if(isWhite == false && dirLR != Move.CENTER){
					move = new Move(row,column,row+dirUD,column+dirLR);
				}
				
				break;
				
			case BoardUtils.B_PUSHER:
				if(isWhite == true && dirLR != Move.CENTER){
					move = new Move(row,column,row+dirUD,column+dirLR);
				}
				break;
				
			case BoardUtils.W_PUSHABLE:
				if((isWhite == false) && (dirLR != Move.CENTER)){
					move = new Move(row,column,row+dirUD,column+dirLR);
				} else if((isWhite == true) &&	// 1)
						  (column+dirLR != Board.COLUMN_A || dirLR != Move.LEFT) && (column+dirLR != Board.COLUMN_H || dirLR != Move.RIGHT) &&	// 2)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtils.W_PUSHER) &&	// 3)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtils.W_PUSHABLE) &&	// 3)
						  ((board.getSquareState(row+(2*dirUD),column+(2*dirLR)) == BoardUtils.EMPTY) || (dirLR != Move.CENTER))){	// 4)
					move = new Move(row+dirUD,column+dirLR,row+(2*dirUD),column+(2*dirLR));	
				}
				break;
				
			case BoardUtils.B_PUSHABLE:
				if((isWhite == true) && (dirLR != Move.CENTER)){
					move = new Move(row,column,row+dirUD,column+dirLR);
				} else if((isWhite == false) &&	// 1)
						  (column+dirLR != Board.COLUMN_A || dirLR != Move.LEFT) && (column+dirLR != Board.COLUMN_H || dirLR != Move.RIGHT) &&	// 2)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtils.B_PUSHER) &&	// 3)
						  (board.getSquareState(row+(2*dirUD),column+(2*dirLR)) != BoardUtils.B_PUSHABLE) &&	// 3)
						  ((board.getSquareState(row+(2*dirUD),column+(2*dirLR)) == BoardUtils.EMPTY) || (dirLR != Move.CENTER))){	// 4)
					move = new Move(row+dirUD,column+dirLR,row+(2*dirUD),column+(2*dirLR));	
				}
				break;
				
			default:
				move = new Move(row,column,row+dirUD,column+dirLR);
			}
		}
		return move;
	}
}