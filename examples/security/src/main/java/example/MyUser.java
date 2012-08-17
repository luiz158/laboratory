package example;

import java.util.HashMap;
import java.util.Map;

import br.gov.frameworkdemoiselle.security.User;

public class MyUser implements User {

	private static final long serialVersionUID = 1L;

	private final String username;

	private Map<String, Object> attrs = new HashMap<String, Object>();

	public MyUser(String username) {
		this.username = username;
	}

	@Override
	public String getId() {
		return this.username;
	}

	@Override
	public Object getAttribute(Object key) {
		return this.attrs.get(key);
	}

	@Override
	public void setAttribute(Object key, Object value) {
		this.attrs.put((String) key, value);
	}
}
