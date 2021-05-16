package com.insside.app.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.insside.app.bowling.service.IBowlingService;
import com.insside.app.bowling.service.IFileService;
import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class BowlingServiceTest {
	
	@Autowired
	private IBowlingService bowlingServiceImpl;
	
	@Autowired
	private IFileService fileServiceImpl;
	
	
	
	@Test
	public void shouldScoreZeroIfTheyAreAllFaults() {
		
		List<Launch> plays = fileServiceImpl.getPlays("./src/main/resources/testresources/jugadas_todos_F.txt");
		List<String> names = plays.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
		Map<Boolean, List<Launch>> lists = plays.stream().collect(Collectors.partitioningBy(launch -> launch.getName().equals(names.get(0))));
		
		List<Launch> player1 = lists.get(false);

		List<Launch> player2 = lists.get(true);

		List<Frame> framesPlayer1 = bowlingServiceImpl.getFrames(player1);
		
		List<Frame> framesPlayer2 = bowlingServiceImpl.getFrames(player2);
		
		assertEquals(0, framesPlayer1.get(framesPlayer1.size() - 1).getScore());
		assertEquals(0, framesPlayer2.get(framesPlayer1.size() - 1).getScore());
		
	}
	
	@Test
	public void shouldScoreZeroIfTheyAreAllZeros() {
		
		List<Launch> plays = fileServiceImpl.getPlays("./src/main/resources/testresources/jugadas_todos_Ceros.txt");
		List<String> names = plays.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
		Map<Boolean, List<Launch>> lists = plays.stream().collect(Collectors.partitioningBy(launch -> launch.getName().equals(names.get(0))));
		
		List<Launch> player1 = lists.get(false);

		List<Launch> player2 = lists.get(true);

		List<Frame> framesPlayer1 = bowlingServiceImpl.getFrames(player1);
		
		List<Frame> framesPlayer2 = bowlingServiceImpl.getFrames(player2);
		
		assertEquals(0, framesPlayer1.get(framesPlayer1.size() - 1).getScore());
		assertEquals(0, framesPlayer2.get(framesPlayer1.size() - 1).getScore());
		
	}
	
	
	@Test
	public void shouldScoreThreeHundredIfTheyAreAllStrikes() {
		List<Launch> plays = fileServiceImpl.getPlays("./src/main/resources/testresources/jugadas_todos_Strikes.txt");
		List<String> names = plays.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
		Map<Boolean, List<Launch>> lists = plays.stream().collect(Collectors.partitioningBy(launch -> launch.getName().equals(names.get(0))));
		
		List<Launch> player1 = lists.get(false);

		List<Launch> player2 = lists.get(true);

		List<Frame> framesPlayer1 = bowlingServiceImpl.getFrames(player1);
		
		List<Frame> framesPlayer2 = bowlingServiceImpl.getFrames(player2);
		
		assertEquals(300, framesPlayer1.get(framesPlayer1.size() - 1).getScore());
		
		assertEquals(300, framesPlayer2.get(framesPlayer1.size() - 1).getScore());
		
	}
	
	@Test
	public void twoPlayerScoringTest() {
		List<Launch> plays = fileServiceImpl.getPlays("./src/main/resources/testresources/dos_jugadores.txt");
		List<String> names = plays.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
		Map<Boolean, List<Launch>> lists = plays.stream().collect(Collectors.partitioningBy(launch -> launch.getName().equals(names.get(0))));
		
		List<Launch> player1 = lists.get(false);

		List<Launch> player2 = lists.get(true);

		List<Frame> framesPlayer1 = bowlingServiceImpl.getFrames(player1);
		
		List<Frame> framesPlayer2 = bowlingServiceImpl.getFrames(player2);
		
		assertEquals(151, framesPlayer1.get(framesPlayer1.size() - 1).getScore());
		
		assertEquals(167, framesPlayer2.get(framesPlayer1.size() - 1).getScore());
		
	}
	
	
	

	
}
