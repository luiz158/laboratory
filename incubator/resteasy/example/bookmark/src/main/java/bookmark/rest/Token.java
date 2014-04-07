package bookmark.rest;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Token {

	private String value;

	@Override
	public String toString() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
