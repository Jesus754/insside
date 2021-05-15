package com.insside.app.bowling;

import java.util.List;
import java.util.Map;
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
		
		if (names.size() != 2) {
			logger.error("Debe haber dos jugadores");
		}
		
		Map<Boolean, List<Launch>> lists = plays.stream().collect(Collectors.partitioningBy(launch -> launch.getName().equals(names.get(0))));
		
		List<Launch> player1 = lists.get(false);
		List<Launch> player2 = lists.get(true);
		
		List<Frame> framesPlayer1 = bowlingServiceImpl.getFrames(player1);
		
		List<Frame> framesPlayer2 = bowlingServiceImpl.getFrames(player2);
		
		fileServiceImpl.exportResults(framesPlayer1,player1.get(0).getName(), framesPlayer2, player2.get(0).getName());
		
	
	}

	
	

		
	

}
