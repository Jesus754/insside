package com.insside.app.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.insside.app.bowling.service.FileServiceImpl;
import com.insside.app.bowling.service.IBowlingService;
import com.insside.app.bowling.service.IFileService;
import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

@SpringBootApplication
public class BowlingApplication {
	

	

	private static IFileService fileServiceImpl;
	
	private static IBowlingService bowlingServiceImpl;
	
	static Logger logger = Logger.getLogger(BowlingApplication.class);
	
	@Autowired
	public void setFileService(FileServiceImpl fileServiceImpl) {
		BowlingApplication.fileServiceImpl = fileServiceImpl;
	}
	
	@Autowired
	public void setBowlingService(IBowlingService bowlingServiceImpl) {
		BowlingApplication.bowlingServiceImpl = bowlingServiceImpl;
	}

	public static void main(String[] args) {
		SpringApplication.run(BowlingApplication.class, args);
		
		List<Launch> plays = fileServiceImpl.getPlays(args[0]);
		
		List<String> names = plays.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
		
		if (names.size() < 2) {
			logger.info("********************************************************************************");
            logger.info("La cantidad de jugadores debe ser mayor o igual a 2");
            logger.info("********************************************************************************");
			throw new IllegalArgumentException();
		}
		List<List<Frame>> lists = new ArrayList<>();;
		for (String name : names) {
			List<Launch> list = plays.stream().filter(launch -> launch.getName().equals(name)).collect(Collectors.toList());
			List<Frame> listFrame = bowlingServiceImpl.getFrames(list);
			lists.add(listFrame);
		}
	
		fileServiceImpl.exportResults(lists,args[0]);
		
		
	}

}
