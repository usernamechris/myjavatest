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
		
		// 세 개 이상의 파라미터를 전달하는 경우는 Object 배열을 활용해야 한다
		Object[] params =  new Object[]{"1", 2, 3, 4, 5, 6};
	    logger.info("{} {} {} {} {} {}", params);
	}

}
