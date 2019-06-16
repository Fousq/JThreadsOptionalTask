package kz.zhanbolat.jthreads;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Airport;
import kz.zhanbolat.jthreads.entity.Passager;
import kz.zhanbolat.jthreads.generator.PassagerGenerator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private static Logger logger = LogManager.getLogger(AppTest.class);
	
    @Test
    public void Log4j2Test() {
    	 logger.info("INFO");
    	 logger.debug("DEBUG");
    	 logger.error("ERROR");
    	 logger.fatal("FATAL");
    }
    
    public void AppTest() {
    	PassagerGenerator passagerGen = new PassagerGenerator();
    	Airport airport = Airport.getInstance();
    	int passagersNumber = 100;
    	int terminalsNumber = 4;
    	List<Passager> passagers = new ArrayList<>(100);
    	for (int i = 0; i < passagersNumber; i++) {
    		airport.addPassager(passagerGen.generate(4));
    	}
    }
    
}
