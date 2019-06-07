package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void Log4j2Test() {
    	 Logger logger = LogManager.getLogger(getClass());
    	 System.out.println(logger.isDebugEnabled());
    	 logger.info("INFO");
    	 logger.debug("DEBUG");
    	 logger.error("ERROR");
    	 logger.fatal("FATAL");
    }
    
}
