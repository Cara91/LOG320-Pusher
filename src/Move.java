
public class Move {

	private int fromRow;
	private int fromColumn;
	private int toRow;
	private int toColumn;
	
	public Move(int fromRow, int fromColumn, int toRow, int toColumn){
		
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
		
	}

	public int getFromLetter() {
		return fromRow;
	}

	public void setFromLetter(int fromLetter) {
		this.fromRow = fromLetter;
	}

	public int getFromNumber() {
		return fromColumn;
	}

	public void setFromNumber(int fromNumber) {
		this.fromColumn = fromNumber;
	}

	public int getToLetter() {
		return toRow;
	}

	public void setToLetter(int toLetter) {
		this.toRow = toLetter;
	}

	public int getToNumber() {
		return toColumn;
	}

	public void setToNumber(int toNumber) {
		this.toColumn = toNumber;
	}
	
	public String toString(){
		
		String str = new String();

		str = str.concat(getLetterString(fromColumn));
		str = str.concat(getNumberString(fromRow));
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
	
}
