package example.logger;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class LoggerExampleTest {

	@Inject
	private LoggerExample logger;

	@Test
	public void loggerDebugTest() {
		logger.loggerDebug();
		assertTrue(true);
	}
}
