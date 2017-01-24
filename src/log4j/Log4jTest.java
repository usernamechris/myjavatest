package log4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {

	private static Logger logger = LoggerFactory.getLogger(Log4jTest.class);

	
	@Test
	public void write() {

		logger.trace("TRACE");
		logger.debug("DEBUG" );
		logger.info("INFO");
		logger.warn("WARN");
		logger.error("ERROR");
		
		logger.debug("----------------------------");
		
		logger.debug("value: ", 1234);
		logger.debug("value: {}", 1234);

		logger.debug("----------------------------");

		logger.debug("value: {}", 1234, 567);
		logger.debug("value: {}{}", 1234, 567);
		logger.debug("value: {}, {}", 1234, 567);

		logger.debug("----------------------------");

		logger.debug("value: {}, {}", "hi", 567);
		
		logger.debug("----------------------------");

		logger.debug("value: {123}, {}", "hi", 567);// value: {123}, hi 
		
		logger.debug("value: {{}123}, {}", "hi", 567);// value: {hi123}, 567 

		logger.debug("value: {{123}}, {}", "hi", 567);// {{123}}, hi 
	}
}
