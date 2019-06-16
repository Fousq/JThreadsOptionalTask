package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Ladder;
import kz.zhanbolat.jthreads.generator.LadderGenerator;
import kz.zhanbolat.jthreads.generator.PlaneGenerator;
import kz.zhanbolat.jthreads.generator.TerminalGenerator;

public class LadderGeneratorTest {
	private static Logger logger = LogManager.getLogger(LadderGeneratorTest.class);
	private static PlaneGenerator planeGen;
	private static TerminalGenerator terminalGen;
	
	@BeforeClass
	public static void init() {
		planeGen = new PlaneGenerator();
		terminalGen = new TerminalGenerator();
	}
	
	@Test
	public void ladderShouldBeCreated() {
		LadderGenerator ladderGen = new LadderGenerator();
		Ladder ladder = ladderGen.generate();
		logger.debug(ladder);
		assertTrue(ladder.getPlane() == null);
		assertTrue(ladder.getTerminal() == null);
		assertEquals(ladder.getPassagers().size(), 0);
		ladder = ladderGen.generate(terminalGen.generate(), planeGen.generate(1));
		logger.debug(ladder);
		assertTrue(ladder.getPlane() != null);
		assertTrue(ladder.getTerminal() != null);
		assertEquals(ladder.getPassagers().size(), 0);
	}
	
}
