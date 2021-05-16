package com.insside.app.bowling.service;

import java.util.List;

import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

public interface IFileService {

	public List<Launch> getPlays(String url);	
	
	public void exportResults(List<List<Frame>> lists, String url);
}
