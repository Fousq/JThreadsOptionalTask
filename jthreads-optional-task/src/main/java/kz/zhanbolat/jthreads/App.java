package kz.zhanbolat.jthreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.PassagerTarget;
import kz.zhanbolat.jthreads.entity.Plane;
import kz.zhanbolat.jthreads.entity.Terminal;
import kz.zhanbolat.jthreads.exception.PlaneException;
import kz.zhanbolat.jthreads.generator.LadderGenerator;
import kz.zhanbolat.jthreads.generator.PassagerGenerator;
import kz.zhanbolat.jthreads.generator.PlaneGenerator;
import kz.zhanbolat.jthreads.generator.TerminalGenerator;

/**
 * Hello world!
 *
 */
public class App 
{
	static Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {
        int choice;
        while (true) {
        	logger.info("Choose action to performe: 1.Landing 2.Disembarkation "
        				+ "3. Finish the app");
        	logger.info("Your choice: ");
        	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        	try {
        		choice = Integer.parseInt(bf.readLine());
        	} catch (IOException e) {
        		logger.error("Error in input stream", e);
        		choice = 3;
        	}
        	if (choice == 3) {
        		return;
        	}
        	Airport airport = Airport.getInstance();
        	int terminalsNumber;
        	do {
        		logger.info("Enter the number of terminals: ");
        		try {
        			terminalsNumber = Integer.parseInt(bf.readLine());
        			if (terminalsNumber < 4 || terminalsNumber > 6) {
        				logger.info("The number of terminals should be between "
        							+ "4 and 6");
        			}
        		} catch (IOException e) {
        			logger.error("Error in input stream", e);
        			terminalsNumber = 0;
        			return;
        		}
        	} while(terminalsNumber < 4 || terminalsNumber > 6);
        	List<Terminal> terminals = new ArrayList<>(terminalsNumber);
        	List<Ladder> ladders = new ArrayList<>(terminalsNumber);
        	int planesNumber;
        	int passagersNumber;
        	logger.info("Enter the number of planes: ");
        	try {
        		planesNumber = Integer.parseInt(bf.readLine());
        	} catch(IOException e) {
        		logger.error("Error in input stream", e);
        		planesNumber = 0;
        		return;
        	}
        	List<Plane> planes = new ArrayList<>(planesNumber);
        	PassagerGenerator passagerGen = new PassagerGenerator();
        	PlaneGenerator planeGen = new PlaneGenerator();
        	LadderGenerator ladderGen = new LadderGenerator();
        	TerminalGenerator terminalGen = new TerminalGenerator();
        	List<Passager> passagers = null;
        	switch (choice) {
        	case 1:
        		logger.info("Enter the number of passagers per plane: ");
        		try {
        			passagersNumber = Integer.parseInt(bf.readLine());
        		} catch (IOException e) {
        			logger.error("Error in input stream", e);
        			passagersNumber = 0;
        			return;
        		}
        		passagers = new ArrayList<>(passagersNumber);
        		for (int i = 0; i < planesNumber; i++) {
        			for (int j = 0; j < passagersNumber; j++) {
        				Passager passager = passagerGen.generate(terminalsNumber, 
        														PassagerTarget.TO_LANT,
        														planesNumber);
        				passagers.add(passager);
        			}
        			Plane plane = null;
        			try {
        				plane = planeGen.generate(passagersNumber, 
        										new ArrayList<>(passagers));
        				logger.debug(plane);
        				planes.add(plane);
        			} catch (PlaneException e) {
        				logger.error("Error in init the plane.", e);
        				return;
        			}
        			passagers.clear();
        		}
        		airport.setPlanes(planes);
        		for (int i = 0; i < terminalsNumber; i++) {
            		Ladder ladder = ladderGen.generate();
            		Terminal terminal = terminalGen.generate(ladder, airport);
            		ladder.setTerminal(terminal);
            		terminal.start();
            		ladder.start();
            	}
        		break;
        	case 2:
        		logger.info("Enter the number of passagers: ");
        		try {
        			passagersNumber = Integer.parseInt(bf.readLine());
        		} catch (IOException e) {
        			logger.error("Error in input stream", e);
        			passagersNumber = 0;
        			return;
        		}
        		passagers = new ArrayList<>(passagersNumber);
        		for (int i = 0; i < passagersNumber; i++) {
            		Passager passager = passagerGen.generate(terminalsNumber, 
        					  							PassagerTarget.TO_DISAMBARK,
        					  							planesNumber);
            		passagers.add(passager);
            	}
            	for (int i = 0; i < planesNumber; i++) {
            		Plane plane = planeGen.generate(passagersNumber); 
            		planes.add(plane);
            	}
            	airport.setPassagers(passagers);
            	airport.setPlanes(planes);
            	for (int i = 0; i < terminalsNumber; i++) {
            		Ladder ladder = ladderGen.generate();
            		Terminal terminal = terminalGen.generate(ladder, airport);
            		ladder.setTerminal(terminal);
            		terminal.start();
            		ladder.start();
            		airport.disembark(terminal);
            	}
            	break;
        	}
        }
    }
}
