
public class Move {

	private int fromLetter;
	private int fromNumber;
	private int toLetter;
	private int toNumber;
	
	public Move(int fromLetter, int fromNumber, int toLetter, int toNumber){
		
		this.fromLetter = fromLetter;
		this.fromNumber = fromNumber;
		this.toLetter = toLetter;
		this.toNumber = toNumber;
		
	}

	public int getFromLetter() {
		return fromLetter;
	}

	public void setFromLetter(int fromLetter) {
		this.fromLetter = fromLetter;
	}

	public int getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(int fromNumber) {
		this.fromNumber = fromNumber;
	}

	public int getToLetter() {
		return toLetter;
	}

	public void setToLetter(int toLetter) {
		this.toLetter = toLetter;
	}

	public int getToNumber() {
		return toNumber;
	}

	public void setToNumber(int toNumber) {
		this.toNumber = toNumber;
	}
	
	public String toString(){
		
		String str = new String();

		str = str.concat(getLetterString(fromLetter));
		str = str.concat(getNumberString(fromNumber));
		str = str.concat(getLetterString(toLetter));
		str = str.concat(getNumberString(toNumber));
		
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
