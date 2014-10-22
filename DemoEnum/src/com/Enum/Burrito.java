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
