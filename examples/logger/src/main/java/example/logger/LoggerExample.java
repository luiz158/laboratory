package example.logger;

import javax.inject.Inject;

import org.slf4j.Logger;

public class LoggerExample {

	@Inject
	private Logger logger;
	
	public void loggerDebug() {
		logger.debug("teste");
	}
	
}
