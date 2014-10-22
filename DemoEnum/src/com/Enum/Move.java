package com.Enum;

public class Move {

	public static void main(String[] args) {

		action(ActionConstants.MOVE_BACKWORDS);
		action(1);
		action(5);
	}

	public static void action(int a) {

		switch (a) {
		case ActionConstants.MOVE_BACKWORDS:
			System.out.println("Back!!!");
			break;
		case ActionConstants.MOVE_FORWARDS:
			System.out.println("Go GO GO !!!");
			break;
		case ActionConstants.STOP:
			System.out.println("STOP!!!");
			break;
		default:
			System.out.println("Error!!!");
		}

	}
}
