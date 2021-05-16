package com.insside.app.bowling.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.insside.app.bowling.util.Frame;
import com.insside.app.bowling.util.Launch;

@Service
public class FileServiceImpl implements IFileService{
	
	static Logger logger = Logger.getLogger(FileServiceImpl.class);
	
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
				if (this.validateFormat(parts)) {
					launch.setName(parts[0]);
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
	
	
	public void exportResults(List<List<Frame>> lists, String url) {
        
		FileWriter fw=null;
		BufferedWriter bw=null;
		try
        {
			
			Path path = Paths.get(url);
			String directory = path.getParent().toString() + File.separator +"resultado.txt";
			
			
            File file = new File(directory);
            
          
            if (!file.exists()) {
                file.createNewFile();
            }
        	
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            
            bw.write("Frame");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("1");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("2");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("3");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("4");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("5");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("6");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("7");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("8");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("9");
        	bw.write("\t");
        	bw.write("\t");
        	bw.write("10");
    
           
            for (List<Frame> listPlayer : lists) {
            	bw.write("\n");
	            bw.write(listPlayer.get(0).getName());
	            
	            bw.write("\n");
	            bw.write("Pinfalls");
	            bw.write("\t");
	            for (Frame frame : listPlayer) {
	            	bw.write(frame.getFirstLaunch());
	            	bw.write("\t");
	            	bw.write(frame.getSecondLaunch());
	            	bw.write("\t");
	            	if (frame.getFrame() == 10 && frame.getThirdLaunch() != null) {
	            		bw.write(frame.getThirdLaunch());
	            	}
	            }
	           
	            bw.write("\n");
	            bw.write("Score");
	            for (Frame frame : listPlayer) {
	            	bw.write("\t");
	            	bw.write("\t");
	            	bw.write(frame.getScore().toString());
	            }
	            
            }
            logger.info("********************************************************************************");
            logger.info("Archivo generado en ruta: " + directory);
            logger.info("********************************************************************************");
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
	
	private boolean validateFormat(String[] parts) throws IllegalArgumentException {

		try {
			if (parts.length < 2) {
				throw new IllegalArgumentException();
			}
			if (FOUL.equals(parts[1]))
				return true;
			Integer aux = Integer.valueOf(parts[1]);
		if (aux <= 10  && aux >= 0)
			return true;
		throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			logger.error("********************************************************************************");
	        logger.error("La puntuacion debe ser un numero del 1 al 10 o F");
	        logger.error("********************************************************************************");
	        throw new IllegalArgumentException();
		}
	}
}
