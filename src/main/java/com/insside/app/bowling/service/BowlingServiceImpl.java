package com.insside.app.bowling.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

@Service
public class BowlingServiceImpl implements IBowlingService {
	
	static Logger logger = Logger.getLogger(BowlingServiceImpl.class);

	private static final String F = "F";
	private static final String X = "X";
	private static final String EMPTY = " ";
	private static final String COMPLETE = "/";
	private static final Integer MAX = 10;
	private static final Integer MAX_ROUND = 10;
	private static final Integer FOUL = -1;

	public List<Frame> getFrames(List<Launch> scores) {
		Integer currentScore;
		Integer totalScore = 0;
		Integer round = 1;
		Integer firstLaunchForwardScore;
		Integer secondLaunchForwardScore;
		List<Frame> frames = new ArrayList<Frame>(); 
		for (int i = 0; i < scores.size(); i++) {
			if(round > 10) {
				 this.logMessage(scores.get(0).getName() + " no puede tener mas de 10 lanzamientos");
				 throw new IllegalArgumentException();
			}
			currentScore = getScore(i,scores);
			firstLaunchForwardScore = getScore(i + 1,scores);
			secondLaunchForwardScore = getScore(i + 2, scores);
			Frame frame = new Frame();
			frame.setFrame(round);
			frame.setName(scores.get(i).getName());
			
			if (round != MAX_ROUND && isStrike(currentScore)) {
				frame.setFirstLaunch(EMPTY);
				frame.setSecondLaunch(X);
				if (secondLaunchForwardScore == null) {
					this.logMessage("La cantidad de lanzamientos de " + scores.get(0).getName() + " en la ronda " + round + " esta incompleta");
					throw new IllegalArgumentException();
				}
				totalScore = totalScore + currentScore + (isFoul(firstLaunchForwardScore) ? 0 : firstLaunchForwardScore) + (isFoul(secondLaunchForwardScore) ? 0 : secondLaunchForwardScore);
				frame.setScore(totalScore);
				frames.add(frame);
				round++;
					
			} else if (round == MAX_ROUND && isStrike(currentScore)) {
				frame.setFirstLaunch(X);
				frame.setSecondLaunch((!isFoul(firstLaunchForwardScore)) ? (firstLaunchForwardScore == MAX) ? X : firstLaunchForwardScore.toString() : F);
				if (secondLaunchForwardScore == null) {
					this.logMessage("La cantidad de lanzamientos de " + scores.get(0).getName() + " en la ronda " + round + " esta incompleta");
					throw new IllegalArgumentException();
				}
				frame.setThirdLaunch(!isFoul(secondLaunchForwardScore) ? (secondLaunchForwardScore == MAX) ? X : secondLaunchForwardScore.toString() : F);
				totalScore = totalScore + currentScore + (isFoul(firstLaunchForwardScore) ? 0 : firstLaunchForwardScore) + (isFoul(secondLaunchForwardScore) ? 0 : secondLaunchForwardScore);
				frame.setScore(totalScore);
				frames.add(frame);
				round++;
				i = i + 2;
			} else {
				frame.setFirstLaunch((!isFoul(currentScore)) ? currentScore.toString() : F);
				if (firstLaunchForwardScore == null) { 
					this.logMessage("La cantidad de lanzamientos de " + scores.get(0).getName() + " en la ronda " + round + " esta incompleta");
					throw new IllegalArgumentException();
				}
				if (currentScore + (isFoul(firstLaunchForwardScore) ? 0 : firstLaunchForwardScore) >= MAX) {
					frame.setSecondLaunch((!isFoul(currentScore))? COMPLETE : F);
					if (secondLaunchForwardScore == null) {
						this.logMessage("La cantidad de lanzamientos de " + scores.get(0).getName() + " en la ronda " + round + " esta incompleta");
						throw new IllegalArgumentException();
					}
					totalScore = totalScore + MAX + (isFoul(secondLaunchForwardScore) ? 0 : secondLaunchForwardScore);
					 
				} else {
					frame.setSecondLaunch((!isFoul(firstLaunchForwardScore))?firstLaunchForwardScore.toString() : F);
					totalScore = totalScore + (isFoul(currentScore) ? 0 : currentScore) + (isFoul(firstLaunchForwardScore) ? 0 :firstLaunchForwardScore);
				}
				frame.setScore(totalScore);
				frames.add(frame);
				if (round != MAX_ROUND)
					i++;
				else {
					
					if (currentScore + firstLaunchForwardScore < MAX) {
						if (secondLaunchForwardScore != null) {
							this.logMessage(scores.get(0).getName() + " no tiene permitido el tercer tiro en la ronda 10");
							throw new IllegalArgumentException();
						}
						break;
					} else {
						i = i + 2;
						frame.setThirdLaunch(!isFoul(secondLaunchForwardScore) ? ((secondLaunchForwardScore == MAX)  ? X : secondLaunchForwardScore.toString()) : F);
					}
				}
				round++;
			
			}
		}
		if (round < 10) {
			this.logMessage(scores.get(0).getName() + " no completa las 10 rondas");
			throw new IllegalArgumentException();
		}
		return frames;
	};

	private Integer getScore(Integer i, List<Launch> scores) {
		if ( i >=  scores.size()) 
		    return null;
		if (F.equals(scores.get(i).getScore())) {
			return FOUL;
		}
		return Integer.valueOf(scores.get(i).getScore());
	}

	private Boolean isFoul(Integer currentScore) {
		if (currentScore == FOUL) {
			return true;
		}
		return false;
	}

	private Boolean isStrike(Integer currentScore) {
		if (currentScore == MAX) {
			return true;
		}
		return false;
	}
	
	private void logMessage(String message) {
		logger.error("********************************************************************************");
        logger.error(message);
        logger.error("********************************************************************************");
	}
	

}
