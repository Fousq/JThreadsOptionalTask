package kz.zhanbolat.jthreads;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

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

public class LandingActionTest {
	private static Logger logger = LogManager.getLogger(LandingActionTest.class);
	private int passagersNumber;
	private int terminalsNumber;
	private int planesNumber;
	private int planeCapacity;
	private PassagerGenerator passagerGen;
	private TerminalGenerator terminalGen;
	private LadderGenerator ladderGen;
	private PlaneGenerator planeGen;
	private Airport airport;
	
	@Before
	public void init() {
		passagersNumber = 20;
    	terminalsNumber = 4;
    	planesNumber = 10;
    	planeCapacity = 100;
		passagerGen = new PassagerGenerator();
		terminalGen = new TerminalGenerator();
		ladderGen = new LadderGenerator();
		planeGen = new PlaneGenerator();
    	airport = Airport.getInstance();
	}
	
	@Test
	public void test() {
		List<Passager> passagers = new ArrayList<>(passagersNumber);
		List<Plane> planes = new ArrayList<>(planesNumber);
		for (int i = 0; i < planesNumber; i++) {
			for (int j = 0; j < passagersNumber; j++) {
				Passager passager = passagerGen.generate(terminalsNumber, 
														PassagerTarget.TO_LANT,
														planesNumber);
				logger.debug(passager);
				passagers.add(passager);
			}
			Plane plane = null;
			try {
				plane = planeGen.generate(planeCapacity, 
										new ArrayList<>(passagers));
				logger.debug(plane);
				planes.add(plane);
			} catch (PlaneException e) {
				logger.error("Error in init the plane.", e);
				fail();
			}
			passagers.clear();
		}
		airport.setPlanes(planes);
		logger.debug("Planes size: " + airport.planesSize());
		List<Terminal> terminals = new ArrayList<>(terminalsNumber);
    	List<Ladder> ladders = new ArrayList<>(terminalsNumber);
    	for (int i = 0; i < terminalsNumber; i++) {
    		Ladder ladder = ladderGen.generate();
    		Terminal terminal = terminalGen.generate(ladder, airport);
    		ladder.setTerminal(terminal);
    		terminal.start();
    		ladder.start();
    	}
	}
	
}
