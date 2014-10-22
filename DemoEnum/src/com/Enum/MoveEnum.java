package com.Enum;

public class MoveEnum {

	public static void main(String[] args) {
		Action(ActionEnum.MOVE_FORWARDS);
		//Action("asd");
	}
	
	public static void Action(ActionEnum e) {
		switch(e) {
		case MOVE_BACKWORDS :
			System.out.println("Back!!!");
			break;
		case MOVE_FORWARDS :
			System.out.println("Go Go Go!!!");
			break;
		case STOP :
			System.out.println("Stop!!!");
			break;
//		case WOW :
//			System.out.println("WOW");
//			break;
		}
	}
}
