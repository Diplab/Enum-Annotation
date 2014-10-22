package com.Enum;

public interface Food {
	
	enum Fruit implements Food {
		APPLE,BANANA,ORANGE;
	}
	
	enum Candy implements Food {
		LOLIPOP,CHOCOLATE,HONEY;
	}
	enum Coffee implements Food {
		BLACK_COFFEE,LATTE,ESPRESSO;
	}
	
}
