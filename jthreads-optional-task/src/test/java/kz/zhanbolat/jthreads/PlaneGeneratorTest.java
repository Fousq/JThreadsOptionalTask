package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.entity.Plane;
import kz.zhanbolat.jthreads.exception.PlaneException;
import kz.zhanbolat.jthreads.generator.PassagerGenerator;
import kz.zhanbolat.jthreads.generator.PlaneGenerator;

public class PlaneGeneratorTest {
	private static Logger logger = LogManager.getLogger(PlaneGeneratorTest.class);
	private static List<Passager> passagers;
	private static PassagerGenerator passagerGen;
	private static int id;
	
	@BeforeClass
	public static void init() {
		id = 0;
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
	
	@Test
	public void planeShouldBeCreatedUsingRandom() {
		PlaneGenerator planeGen = new PlaneGenerator();
		Plane plane = planeGen.generateWithRandomCapacity(100);
		logger.debug(plane);
		assertTrue(plane.getId() == ++id);
		assertTrue(plane.getCapacity() != -1);
		assertTrue(plane.getPassagers() != null);
		try {
			plane = planeGen.generateWithRandomCapacity(100, passagers);
			logger.debug(plane);
			assertTrue(plane.getId() == ++id);
			assertTrue(plane.getCapacity() != -1);
			assertTrue(plane.getPassagers() != null);
			assertTrue(plane.getPassagers().size() == 4);
		} catch (PlaneException e) {
			logger.error("Got problem in creating the plane.", e);
			fail();
		}
	}
	
	@Test
	public void planeShouldBeCreated() {
		PlaneGenerator planeGen = new PlaneGenerator();
		Plane plane = planeGen.generate(100);
		logger.debug(plane);
		assertTrue(plane.getId() == ++id);
		assertTrue(plane.getCapacity() != -1);
		assertTrue(plane.getPassagers() != null);
		try {
			plane = planeGen.generate(100, passagers);
			logger.debug(plane);
			assertTrue(plane.getId() == ++id);
			assertTrue(plane.getCapacity() != -1);
			assertTrue(plane.getPassagers() != null);
			assertTrue(plane.getPassagers().size() == 4);
		} catch (PlaneException e) {
			logger.error("Got problem in creating the plane.", e);
			fail();
		}
	}
	
	@Test
	public void generatorShouldThrowException() {
		PlaneGenerator planeGen = new PlaneGenerator();
		try {
			Plane plane = planeGen.generate(3, passagers);
			fail();
		} catch (PlaneException e) {
			logger.error("Got problem in creating the plane.", e);
			assertEquals(e.getMessage(), "Passagers is more than the capacity of"
					+ " the plane");
		}
	}
	
}
