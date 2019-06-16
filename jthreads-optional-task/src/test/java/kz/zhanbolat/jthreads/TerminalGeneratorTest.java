package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.Terminal;
import kz.zhanbolat.jthreads.generator.PassagerGenerator;
import kz.zhanbolat.jthreads.generator.TerminalGenerator;

public class TerminalGeneratorTest {
	private static Logger logger = LogManager.getLogger(TerminalGeneratorTest.class);
	private static TerminalGenerator terminalGen;
	private static PassagerGenerator passagerGen;
	private Ladder ladder;
	private Airport airport;
	private static List<Passager> passagers;
	
	@BeforeClass
	public static void initStatics() {
		terminalGen = new TerminalGenerator();
		passagerGen = new PassagerGenerator();
		passagers = new ArrayList() {
			{
				add(passagerGen.generate(4));
				add(passagerGen.generate(4));
				add(passagerGen.generate(4));
				add(passagerGen.generate(4));
			}
		};
	}
	
	@Before
	public void init() {
		ladder = new Ladder();
		airport = Airport.getInstance();
	}
	
	@Test
	public void terminalShouldBeCreated() {
		int id = 0;
		Terminal terminal = terminalGen.generate();
		logger.debug(terminal);
		assertEquals(terminal.getId(), ++id);
		assertEquals(terminal.getAirport(), null);
		assertEquals(terminal.getLadder(), null);
		assertEquals(terminal.getPassagers().size(), 0);
		terminal = terminalGen.generate(ladder, airport);
		logger.debug(terminal);
		assertEquals(terminal.getId(), ++id);
		assertTrue(terminal.getAirport() != null);
		assertTrue(terminal.getLadder() != null);
		assertEquals(terminal.getPassagers().size(), 0);
		terminal = terminalGen.generate(ladder, airport, passagers);
		logger.debug(terminal);
		assertEquals(terminal.getId(), ++id);
		assertTrue(terminal.getAirport() != null);
		assertTrue(terminal.getLadder() != null);
		assertEquals(terminal.getPassagers().size(), 4);
	}
	
}
