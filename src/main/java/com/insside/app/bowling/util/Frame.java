package com.insside.app.bowling.util;

public class Frame {
	
	private Integer frame;
	
	private String firstLaunch;
	
	private String secondLaunch;
	
	private String  thirdLaunch;
	
	private Integer score;

	public Frame() {
		
	}
	
	public Frame(String player, Integer frame, String launchOne, String launchTwo, Integer score) {
		this.frame = frame;
		this.firstLaunch = launchOne;
		this.secondLaunch = launchTwo;
		this.score = score;
	}

	public Integer getFrame() {
		return frame;
	}

	public void setFrame(Integer frame) {
		this.frame = frame;
	}

	public String getFirstLaunch() {
		return firstLaunch;
	}

	public void setFirstLaunch(String launchOne) {
		this.firstLaunch = launchOne;
	}

	public String getSecondLaunch() {
		return secondLaunch;
	}

	public void setSecondLaunch(String launchTwo) {
		this.secondLaunch = launchTwo;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public final String getThirdLaunch() {
		return thirdLaunch;
	}

	public final void setThirdLaunch(String thirdLaunch) {
		this.thirdLaunch = thirdLaunch;
	}

	@Override
	public String toString() {
		return "Frame [frame=" + frame + ", firstLaunch=" + firstLaunch + ", secondLaunch=" + secondLaunch
				+ ", thirdLaunch=" + thirdLaunch + ", score=" + score + "]";
	}

}
