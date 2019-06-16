package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.PassagerTarget;
import kz.zhanbolat.jthreads.generator.PassagerGenerator;

public class PassagerGeneratorTest {
	private static Logger logger = LogManager.getLogger(PassagerGeneratorTest.class);
	private static PassagerGenerator passagerGen = new PassagerGenerator();
	
	@Test
	public void PassagerShouldBeCreated() {
		int id = 1;
		Passager passager = passagerGen.generate(4, PassagerTarget.NO_TARGET);
		logger.debug(passager);
		assertTrue(passager.getId() == id);
		assertTrue(passager.getPassagerTarget() == PassagerTarget.NO_TARGET);
		passager = passagerGen.generate(4, PassagerTarget.TO_DISAMBARK);
		logger.debug(passager);
		assertTrue(passager.getId() == ++id);
		assertTrue(passager.getPassagerTarget() == PassagerTarget.TO_DISAMBARK);
		passager = passagerGen.generate(4, PassagerTarget.TO_LANT);
		logger.debug(passager);
		assertTrue(passager.getId() == ++id);
		assertTrue(passager.getPassagerTarget() == PassagerTarget.TO_LANT);
	}

}
