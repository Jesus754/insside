package com.insside.app.bowling.service;

import java.util.List;

import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

public interface IBowlingService {
	
	public List<Frame> getFrames(List<Launch> scores);

}
