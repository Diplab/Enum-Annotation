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
