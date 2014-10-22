package com.Enum;
import java.util.EnumSet;

public class Workday {

	public static void main(String[] args) {
		EnumSet<Days> day = EnumSet.noneOf(Days.class);
		
		day.add(Days.MONDAY);
		System.out.println(day);
		
		day.addAll(EnumSet.of(Days.TUSEDAY, Days.WEDNESDAY));
		System.out.println(day);
		
		day = EnumSet.allOf(Days.class);
		System.out.println(day);
		
		day.removeAll(EnumSet.of(Days.SATURDAY, Days.SUNDAY));
		System.out.println(day);
		
		day = EnumSet.complementOf(day);
		System.out.println(day);
	}

}
