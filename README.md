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
- [Annotation](#annotation)
	+ [自訂Annotation](自訂annotation)
	+ [超標註 meta-annotation](超標註-meta-annotation)

	







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



## Annotation

J2SE 5.0 中對 metadata 提出的功能是 Annotation，metadata 就是「資料的資料」（Data about data）。
Annotation提供了Java不足以用來完整描述程式的資訊。Annotation允許我們儲存和程式有關的額外資訊，而此資訊的格式
可受編譯器測試及檢驗，Annotation 對程式運行沒有影響，它的目的在對編譯器或分析工具說明程式的某些資訊


我們可以先來看一下java.lang.Override、java.lang.Deprectated、java.lang.SuppressWarnings 這三個 J2SE 5.0 
中標準的 Annotation 型態

- @Override

Override會對編譯器說明某個方法必須要是重新定義父類別中的方法，編譯器得知這項資訊後，在編譯程式時如果發現被 
@Override 標示的方法並非重新定義父類別中的方法，也就是無法在父類別中找到該方法，就會回報錯誤。

``` java
package com.Annotation;

public class DemoOverride {

	@Override
    public String ToString() {
        return "customObject";
    }	
	public static void main(String[] args) {
		
	}

}
```


- @Deprectated

Deprectated會對編譯器說明某個方法已經不建議使用，如果有開發人員試圖使用或重新定義被@Deprecated標示的方法，編譯
器必須提出警示訊息


``` java
package com.Annotation;

class Something {
	
	@Deprecated Something getSomething() {
		return new Something();
	}
	
}

public class DemoDeprecated {

	public static void main(String[] args) {
		Something some = new Something();
		
		some.getSomething();
	}

}
```


- @SuppressWarnings

SuppressWarnings會對編譯器說明某個方法中若有警示訊息，則加以抑制，不用在編譯完成後出現警訊對編譯器說明某個方法
中若有警示訊息，則加以抑制，不用在編譯完成後出現警訊

``` java
package com.Annotation;

import java.util.HashMap;
import java.util.Map;

public class DemoSupressWarnings {
	
	//@SuppressWarnings(value={"unchecked"})
	 public void doSomething() {
	        Map map = new HashMap();
	        map.put("some", "thing");
	    }
}
```


###  自訂Annotation

我們可以自訂我們需要的Annotation 型態，將資訊提供資訊給我們的程式碼分析工具。

定義的方法有點類似介面不過我們使用的是"@interface"

``` java
package com.Annotation;

public @interface MakeAnnotation {
	
	
}
```
當我們使用 @interface 自行定義 Annotation 型態時，實際上是自動繼承了 java.lang.annotation.Annotation 
介面，並由編譯器自動為您完成其它產生的細節，我們可以為Annotation定義它的內部成員，用處會在稍後介紹

可以用:
- String 
- enum 
- 基礎型別(int float boolean)
- class
- Annotation
- 上述型別的陣列
- 
``` java
package com.Annotation;

public @interface Process {
	public enum Current {NONE, REQUIRE, ANALYSIS, DESIGN, SYSTEM};

    Current current() default Current.NONE;
    String tester();
    String value() default "noMethod";
    boolean ok();
}
```
要注意的是元素皆必須要有它的預設值，或是使用Annotation中的class提供其值，另外非基本型別的元素不能為null值。


###超標註 meta-annotation

用來對標註做標註，一共有四種。

1.@Retention  告知編譯器如何處理 annotaion 

指示編譯器該如何對待您的自定義的 Annotation 型態，預設上編譯器會將 Annotation 資訊留在 .class 
檔案中，但不被虛擬機器讀取，而僅用於編譯器或工具程式運行時提供資訊。

在使用 Retention 型態時，需要提供 java.lang.annotation.RetentionPolicy 的列舉型態，RetentionPolicy 的定義如下所示：

```java
public enum RetentionPolicy {
    SOURCE, // 編譯器處理完Annotation資訊後就沒事了
    CLASS,  // 編譯器將Annotation儲存於class檔中，預設
    RUNTIME // 編譯器將Annotation儲存於class檔中，可由VM讀入
}
```

2.@Target  限定 annotation 使用對象


在定義 Annotation 型態時，您使用 java.lang.annotation.Target 可以定義其適用之時機，在定義時要指定 
java.lang.annotation.ElementType 的列舉值之一：

``` java
public enum ElementType {
     TYPE, // 適用 class, interface, enum
     FIELD, // 適用 field
     METHOD, // 適用 method
     PARAMETER, // 適用 method 上之 parameter
     CONSTRUCTOR, // 適用 constructor
     LOCAL_VARIABLE, // 適用區域變數
     ANNOTATION_TYPE, // 適用 annotation 型態
     PACKAGE // 適用 package
}
```
``` java
package com.Annotation;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MethodAnnotation {

}
```
``` java
package com.Annotation;

@MethodAnnotation
public class DoSomething {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
```

3.@Documented  要求為 API 文件的一部份

在製作 Java Doc 文件時，預設上並不會將 Annotation 的資料加入到文件中，
Annotation 用於標示程式碼以便分析工具使用相關資訊，有時 Annotation 包括了重要的訊息，我們也許會想要在使用者製作
Java Doc 文件的同時，也一併將 Annotation 的訊息加入至 API 文件中，所以在定義 Annotation 型態時，我們可以使用
java.lang.annotation.Documented

4.@Inherited  子類是否繼承父類的 annotation

我們所定義的 Annotation 型態，預設上父類別中的 Annotation 並不會被繼承至子類別中，我們可以在定義 Annotation
型態時加上 java.lang.annotation.Inherited 型態的 Annotation，這讓我們定義的 Annotation
型態在被繼承後仍可以保留至子類別中。



















