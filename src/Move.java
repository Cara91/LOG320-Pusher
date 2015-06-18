
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

		case Board.A:	return "A";
		case Board.B:	return "B";
		case Board.C:	return "C";
		case Board.D:	return "D";
		case Board.E:	return "E";
		case Board.F:	return "F";
		case Board.G:	return "G";
		case Board.H:	return "H";
		default:	return "Error";
		
		}
	}
	
	private String getNumberString(int number){
		
		switch(number){

		case Board.L1:	return "1";
		case Board.L2:	return "2";
		case Board.L3:	return "3";
		case Board.L4:	return "4";
		case Board.L5:	return "5";
		case Board.L6:	return "6";
		case Board.L7:	return "7";
		case Board.L8:	return "8";
		default:	return "Error";
		
		}
	}
	
}
