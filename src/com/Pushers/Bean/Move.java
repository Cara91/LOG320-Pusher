package com.Pushers.Bean;

public class Move implements Comparable<Move>{

    public static final int LEFT = -1;
    public static final int CENTER = 0;
    public static final int RIGHT = 1;

	private int fromRow;
	private int fromColumn;
	private int toRow;
	private int toColumn;
    private int score;


	public Move(int fromRow, int fromColumn, int toRow, int toColumn){
		
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
		
	}

    public Move(int fromRow, int fromColumn, int toRow, int toColumn, int score){

        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
        this.score = score;

    }

	public int getFromRow() {
		return fromRow;
	}

	public void setFromRow(int fromRow) {
		this.fromRow = fromRow;
	}

	public int getFromColumn() {
		return fromColumn;
	}

	public void setFromColumn(int fromColumn) {
		this.fromColumn = fromColumn;
	}

	public int getToRow() {
		return toRow;
	}

	public void setToRow(int toRow) {
		this.toRow = toRow;
	}

	public int getToColumn() {
		return toColumn;
	}

	public void setToColumn(int toColumn) {
		this.toColumn = toColumn;
	}

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }
	
	public String toString(){
		
		String str = new String();

		str = str.concat(getLetterString(fromColumn));
		str = str.concat(getNumberString(fromRow));
		str = str.concat(" - ");
		str = str.concat(getLetterString(toColumn));
		str = str.concat(getNumberString(toRow));
		
		return str;
	}


	
	private String getLetterString(int letter){
		
		switch(letter){

		case Board.COLUMN_A:	return "A";
		case Board.COLUMN_B:	return "B";
		case Board.COLUMN_C:	return "C";
		case Board.COLUMN_D:	return "D";
		case Board.COLUMN_E:	return "E";
		case Board.COLUMN_F:	return "F";
		case Board.COLUMN_G:	return "G";
		case Board.COLUMN_H:	return "H";
		default:	return "Error";
		
		}
	}
	
	private String getNumberString(int number){
		
		switch(number){

		case Board.ROW_1:	return "1";
		case Board.ROW_2:	return "2";
		case Board.ROW_3:	return "3";
		case Board.ROW_4:	return "4";
		case Board.ROW_5:	return "5";
		case Board.ROW_6:	return "6";
		case Board.ROW_7:	return "7";
		case Board.ROW_8:	return "8";
		default:	return "Error";
		
		}
	}

    public void calculateScore(){
        this.score = (int)(Math.random()*1000);
    }

    @Override
    public int compareTo(Move move){
        if(this.score == move.getScore()){
            return 0;
        }else if(this.score > move.getScore()){
            return 1;
        }else{
            return -1;
        }
    }
}
