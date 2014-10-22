package com.Enum;

public enum Course {
	FRUIT(Food.Fruit.class),COFFEE(Food.Coffee.class),CANDY(Food.Candy.class);
	
	private Food[] values;
	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}
	public Food randomSelection() {
		return Enums.random(values);
	}
}
