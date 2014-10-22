package com.Enum;

public enum Transform {
	Car, Robot, Truck, Airplane, Rex;

	public String toString() {
		String id = name();
		String result = "NO!!!";
		switch (id) {
		case "Car":
			result = "Big Bee";
			break;
		case "Robot":
			result = "Transformer";
			break;
		case "Truck":
			result = "Optimus";
			break;
		case "Airplane":
			result = "Some thing that can fly";
			break;
		case "Rex":
			result = "Old Transformer";
			break;
		}

		return result;
	}

	public static void main(String[] args) {

		for (Transform s : values()) {
			System.out.println(s);
		}

	}
}
