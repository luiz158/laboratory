import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import example.Example;


@RunWith(DemoiselleRunner.class)
public class ExampleTest {
	
	@Inject
	Example e;
	
	@Test
	public void print() {
		e.print();
		assertTrue(true);
	}

	
	
}
