package com.Enum;

public class EnumClass {

	public static void main(String[] args) {
		for (Spiciness s : Spiciness.values()) {
			System.out.println(s + " ordinal : " + s.ordinal());
			System.out.println(s.equals(Spiciness.Aatrox));
			System.out.println(s.compareTo(Spiciness.Aatrox));
			System.out.println(s == Spiciness.Aatrox);
			System.out.println(s.getDeclaringClass());
			System.out.println(s.name());
			System.out.println(s.toString());
			System.out.println("------------------------------");
		}
		for (String s : "Wukong Aatrox Ahri".split(" ")) {
			Spiciness sr = Enum.valueOf(Spiciness.class, s);
			System.out.println(sr);
		}

	}

}
