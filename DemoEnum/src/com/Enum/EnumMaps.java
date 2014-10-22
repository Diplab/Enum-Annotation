package com.Enum;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

interface Command {
	void action();
}

public class EnumMaps {

	public static void main(String[] args) {
		EnumMap<Days, Command> em = new EnumMap<Days, Command>(Days.class);
		em.put(Days.MONDAY, new Command() {
			public void action() {
				System.out.println("Coding all day");
			}
		});

		em.put(Days.TUSEDAY, new Command() {
			public void action() {
				System.out.println("Coding half day");
			}
		});

		em.put(Days.WEDNESDAY, new Command() {
			public void action() {
				System.out.println("Half hp left");
			}
		});

		for (Map.Entry<Days, Command> e : em.entrySet()) {
			System.out.print(e.getKey() + " : ");
			e.getValue().action();

		}
		try {
			em.get(Days.THURSDAY).action();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
