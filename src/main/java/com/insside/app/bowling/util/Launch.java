package com.insside.app.bowling.util;

public class Launch {

	private String name; 
	
	private String score;
	
	public Launch() {
		
	}

	public Launch(String name, String score) {
		this.name = name;
		this.score = score;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getScore() {
		return score;
	}

	public final void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Launch [name=" + name + ", score=" + score + "]";
	}	
	
	
	
	
}
