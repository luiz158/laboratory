package exemplo;



import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.util.Parameter;

@RequestScoped
@ViewController
public class ManagedBean {

	@Inject
	private Parameter<String> param1;

	@Inject
	@SessionScoped
	@Name("param2")
	private Parameter<Long> p2;

	public String getParam1() {
		return param1.getValue();
	}

	public Long getParam2() {
		return p2.getValue();
	}
}