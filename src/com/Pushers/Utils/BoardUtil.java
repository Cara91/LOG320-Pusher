package com.Pushers.Utils;

public class BoardUtil {
	public static final int EMPTY = 0;
	public static final int B_PUSHABLE = 1;
	public static final int B_PUSHER = 2;
	public static final int W_PUSHABLE = 3;
	public static final int W_PUSHER = 4;
	
	public static final String DISP_EMPTY = ".";
	public static final String DISP_B_PUSHER = "X";
	public static final String DISP_B_PUSHABLE = "x";
	public static final String DISP_W_PUSHABLE = "o";
	public static final String DISP_W_PUSHER = "O";
	
	public static boolean isAPusher(int state){
		if(state == B_PUSHER || state == W_PUSHER)
			return true;
		return false;
	}

	public static boolean isWhite(int state){
		if(state == W_PUSHER || state == W_PUSHABLE)
			return true;
		return false;
	}

	public static boolean isEmpty(int state){
		if(state == EMPTY)
			return true;
		return false;
	}
	
	public static String stateToString(int state){
		switch(state){
			case B_PUSHER:
				return DISP_B_PUSHER;
			case B_PUSHABLE:
				return DISP_B_PUSHABLE;
			case W_PUSHABLE:
				return DISP_W_PUSHABLE;
			case W_PUSHER:
				return DISP_W_PUSHER;
			default : 
				return DISP_EMPTY;
		}
	}
}
