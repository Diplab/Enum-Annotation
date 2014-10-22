Enum-Annotation
===============

Thinking in Java Ch 19 &amp; Ch 20

- [Enumerated types](#enumerated-types)
	+ [什麼是enum](#什麼是enum)
	+ [在enum出現之前](#在enum出現之前)
	+ [enum的基本用法](#enum的基本用法)
	+ [static imports](#static-imports)
	+ [為enum加入method](#為enum加入method)
	+ [使用interface進行分類組織安排](#使用interface進行分類組織安排)








## Enumerated types

### 什麼是enum
enum是一個特別的class，他繼承自 java.lang.Enum，通常用來定義程式共用的常數，當中的每個成員都預設為
"public static final"


### 在enum出現之前
在撰寫程式時，我們常常會需要用到一些公用的常數，這時我們可以在Java中定義一個類別或介面來
統一管理這些常數，其他物件則從這些類別或介面上取用常數，而當我們需要修改時，可以從這些類別
或介面上直接修改，而不需要更動到程式部分。

J2SE 5.0 中新增了「列舉型態」我們可以用這個功能來取代掉之前版本中，用類別或介面來定義常數的方式，
同時enum還提供了許多在編譯時期的檢察功能

``` java
public class ActionConstants {
	public static final int MOVE_FORWARDS = 1;
	public static final int MOVE_BACKWORDS = 2;
	public static final int STOP = 3;
	
}
```
因為公用常數通常是直接取用且不可修改的，所以在宣告時加上"static final"

``` java
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

```

這是在enum出來之前的作法，作法本身沒錯只是有些小缺陷，action() 所接受的是int型態的常數，
所以我們沒有辦法阻止別人對他輸入ActionConstants規定外的常數，這在使用上會較為不安全，如果輸入
了不正確的數值，同時沒有設計一些防範動作，就有可能造成錯誤。
 

### enum的基本用法

定義enum:
``` java
package com.Enum;

public enum ActionEnum {
	MOVE_FORWARDS, 
	MOVE_BACKWORDS,
	STOP	
}
```
以enumm宣告，其中的成員要以"," 隔開

使用:
``` java
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
```
在Action()中的參數型態是ActionEnum，這時如果我們在Action()中輸入其他型態的引數，編譯器就會
回報錯誤給我們，因為Action()所接受的引數必須是ActionEnum的型態，enum還可以作到更進一步的檢驗
，如果我們在 "switch" 中加入了不屬於 ActionEnum 中列舉的值，編譯器也會回報錯誤給我們。

一些enum常用的方法:
- name() : 回傳所宣稱的名稱。
- toString() : 預設是回傳所宣稱的名稱，但我們可以OverRide它來回傳我們比較想要的東西。
- ordinal() : 回傳int (從0開始) 代表enum中 每個實例的宣告次序。
- compareTo() : 比較兩個enum實例的次序差並回傳(正數,0,負數)。
- equals() : 比較兩個實例的內容是否相等並回傳true or false。
- == : 比較兩個實例是否是同一個物件並回傳true or false。
- getDeclaringClass() : 回傳包覆enum的class。
- valueOf() : 回傳你所傳入的String的enum實例，當找不到時會拋出異常。

其中除了"toString()"之外，其他的方法皆為final，因此我們能OverRide的方法只有"toString()"。

``` java
package com.Enum;

public enum Spiciness {

	Ahri, Wukong, Aatrox, Nocturne, Krad

}
```

``` java
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

```
``` java
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
```

### static imports
將enum實例全部引入本地命名空間，在使用時就無需使用全名

``` java
package com.Enum;
import static com.Enum.Spiciness.*;

public class Burrito {

	Spiciness Hero;

	public Burrito(Spiciness Hero) {
		this.Hero = Hero;
	}

	public void describe() {
		System.out.print("This Champion is ");
		switch (Hero) {
		case Ahri:
			System.out.println("The Nine Tail Fox");
			break;
		case Wukong:
			System.out.println("The Monkey King");
			break;
		case Aatrox:
			System.out.println("The Demon Knight");
			break;
		case Nocturne:
			System.out.println("The Nightmare");
			break;
		case Krad:
			System.out.println("The Excalibur of Angel");
			break;
		default:
			System.out.println("Maybe not a Hero");
		}
	}

	public static void main(String[] args) {

		Burrito one = new Burrito(Spiciness.Aatrox);
		Burrito two = new Burrito(Spiciness.Ahri);
		Burrito thr = new Burrito(Spiciness.Wukong);
		Burrito fou = new Burrito(Spiciness.Nocturne);
		Burrito fiv = new Burrito(Spiciness.Krad);
		Burrito one1 = new Burrito(Aatrox);
		Burrito two2 = new Burrito(Ahri);
		Burrito thr3 = new Burrito(Wukong);
		Burrito fou4 = new Burrito(Nocturne);
		Burrito fiv5 = new Burrito(Krad);
		
		one.describe();
		two.describe();
		thr.describe();
		fou.describe();
		fiv.describe();
		one1.describe();
		two2.describe();
		thr3.describe();
		fou4.describe();
		fiv5.describe();
	}

}
```
### 為enum加入method
enum除了不能被繼承之外，和一般的class沒什麼兩樣，這意謂著我們可以為enum添加methods，enum也可以有main()。

為例舉值提供不同的描述語句:

``` java 
package com.Enum;

public enum Things {
	
	APPLE("An apple a day keeps the doctor away"), BASKETBALL("NBA has the strongest player")
	,Elements("Build everthing in our life");
	
	private String something;
	private Things(String something){
		this.something = something;
	}
	
	public String getSomething() {
		return something;
	}
	
	public static void main(String[] args) {
		for (Things s : Things.values()) {
			System.out.println(s + " : " + s.getSomething());
		}
	}

}
```

### 使用interface進行分類組織安排
enum是繼承自java.lang.Enum，而java並不支援多重繼承，我們無法透過繼承產生enum(不過我們可以在enum實作介面)，
同時因為enum的建構子一定是private的，我們也無法繼承enum。

無法繼承enum有時候會有點難使用，當我們想擴增原始enum中元素的個數，及透過子類別來產生子分類。

不過我們可以通過將元素放到同一介面中，同時基於該介面來產生例舉值，藉以達到將他們歸到同一群組的目的

``` java
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
```
``` java
package com.Enum;

import com.Enum.Food.Candy;
import com.Enum.Food.Coffee;
import com.Enum.Food.Fruit;

public class TypeofFood {

	public static void main(String[] args) {
		Food food = Candy.CHOCOLATE;
			 food = Candy.LOLIPOP;
			 food = Coffee.LATTE;
			 food = Fruit.ORANGE;
	}
}

```
對enum唯一能做的擴充便是透過介面的實作，每個enum都實作外圍的介面Food，便能讓每項事物皆為Food型別。

不過當我們想處理一組型別時，作為enum這樣還不足，假設我們要使用剛剛的enums我們可以撰寫外圍enum使其包含每個
enum中的實例:
``` java
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
```
``` java
package com.Enum;

public class Meal {

	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			for (Course course : Course.values()) {
				Food food = course.randomSelection();
				System.out.println(food);
			}
			System.out.println("---------");
		}
		
	}

}
```

### 使用EnumSet來取代旗標值
當我們需要將多個列舉值組合成一個新物件時，使用J2SE5.0中提供的新的Enumset類別會是一種很好的選擇。EnumSet類別可以用來組合多個列舉值。

EnumSet是基於數度而設計的，它會比HashSet還快，它的內部是利用單一的long值來表示的，此long值會被視為是個位元向量
，所以極快又有效率，但以set來說你無法加入或移除元素，所以當作集合來用不是很有用。

``` java
package com.Enum;

public enum Days {
	SUNDAY,MONDAY,TUSEDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY;
	
}
```
``` java
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
```

EnumSet是建構在longs之上而long是64位元，而每個enum實例都會佔去一個位元，已表示存在或不存在，而當EnumSet有超過單
一long的數量時(64個)，它會在必要時加入另一個long

### 使用EnumMap
特殊的Map，限制它的key值必須是取出單一的enum，因為enum的限制，EnumMap內部可採用array來時做，因此EnumMap超快。

``` java
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
```
EnumSet和EnumMap中元素的順序，都取決於他們在enum中被定義的順序。

### 常數特有函式 Constant-specific methods
為每個enum實例都撰寫不同的methods，而賦予它們不同的行為，為此，你得為enum定義一個或多個 abstract methods，接著
為每個enum實例提供這些methods的定義

Example:
``` java
package com.Enum;

import java.text.DateFormat;
import java.util.Date;

public enum ConstantSpecificMethod {
	
	DATE_TIME {
		String getInfo() {
			return DateFormat.getDateInstance().format(new Date());
		}
	},
	CLASSPATH {
		String getInfo() {
			return System.getenv("CLASSPATH");
		}
	},
	VERSION {
		String getInfo() {
			return System.getProperty("java.version");
		}
	};
	abstract String getInfo();
	public static void main(String[] args) {
		for (ConstantSpecificMethod csm : values()) {
			System.out.println(csm.getInfo());
		}
	}

}
```
我們可以透過相對應的enum實例來搜尋並呼叫methods，這通常會被稱為"表格驅動程式碼"。




