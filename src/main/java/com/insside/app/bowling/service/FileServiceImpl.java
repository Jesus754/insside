package com.insside.app.bowling.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

@Service
public class FileServiceImpl implements IFileService{
	
	private static String FOUL = "F";

	@Override
	public List<Launch> getPlays(String url) {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		String line;
		List<Launch> plays = new ArrayList<Launch>();
		try {
			file = new File(url);
			fr  = new FileReader(file);
			br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(" ");
				Launch launch = new Launch();
				launch.setName(parts[0]);
				if (this.validateFormat(parts[1])) {
					launch.setScore(parts[1]);
					plays.add(launch);
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		      try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
		                            
		       
		}
		return plays;
	
	}
	
	
	public void exportResults(List<Frame> player1,String namePlayer1, List<Frame> player2, String namePlayer2) {
        
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
        {
        	String ruta = "C:/Users/Jesús personal/Desktop/insside/resultado.txt";
  
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
        	
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
          
            List<Integer> framesPlayer1 = player1.stream().map(a -> a.getFrame()).collect(Collectors.toList());
            
            bw.write("Frame");
            for (Integer frame : framesPlayer1) { 
            	bw.write("\t");
            	bw.write("\t");
            	bw.write(frame.toString());
            }
    
            bw.write("\n");
            bw.write(namePlayer2);
            
            bw.write("\n");
            bw.write("Pinfalls");
            bw.write("\t");
            for (Frame frame : player2) {
            	bw.write(frame.getFirstLaunch());
            	bw.write("\t");
            	bw.write(frame.getSecondLaunch());
            	bw.write("\t");
            	if (frame.getFrame() == 10) {
            		bw.write(frame.getThirdLaunch());
            	}
            }
           
            bw.write("\n");
            bw.write("Score");
            for (Frame frame : player2) {
            	bw.write("\t");
            	bw.write("\t");
            	bw.write(frame.getScore().toString());
            }
            
            bw.write("\n");
            bw.write(namePlayer1);  
            
            bw.write("\n");
            bw.write("Pinfalls");
            bw.write("\t");
            for (Frame frame : player1) {
            	bw.write(frame.getFirstLaunch());
            	bw.write("\t");
            	bw.write(frame.getSecondLaunch());
            	bw.write("\t");
            	if (frame.getFrame() == 10) {
            		bw.write(frame.getThirdLaunch());
            	}
            }
            
            bw.write("\n");
            bw.write("Score");
            for (Frame frame : player1) {
            	bw.write("\t");
            	bw.write("\t");
            	bw.write(frame.getScore().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    
	}
	
	private boolean validateFormat(String number) throws NumberFormatException {
		if (FOUL.equals(number))
			return true;
		Integer aux = Integer.valueOf(number);
		if (aux <= 10  && aux >= 0)
			return true;
		throw new NumberFormatException("For input string: " + aux);
	}

}
