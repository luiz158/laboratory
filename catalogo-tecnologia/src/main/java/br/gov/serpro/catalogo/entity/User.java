package br.gov.serpro.catalogo.entity;


public class User implements br.gov.frameworkdemoiselle.security.User {

	private static final long serialVersionUID = 1L;
	
	private String name;

	private String displayName;

	private String email;

	private String telephoneNumber;

	@Override
	public String getId() {
		return name;
	}

	@Override
	public Object getAttribute(Object key) {
		return null;
	}

	@Override
	public void setAttribute(Object key, Object value) {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	/*
	private Long id;

	private String name;

	private String displayName;

	private String email;

	private String telephoneNumber;

	public User() {
	}

	public User(br.gov.frameworkdemoiselle.security.User user) {
		this.id = (Long) user.getAttribute("id");
		this.name = user.getId();
	}
	
	public User(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((telephoneNumber == null) ? 0 : telephoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (telephoneNumber == null) {
			if (other.telephoneNumber != null) {
				return false;
			}
		} else if (!telephoneNumber.equals(other.telephoneNumber)) {
			return false;
		}
		return true;
	}
	
	public br.gov.frameworkdemoiselle.security.User parse() {
		br.gov.frameworkdemoiselle.security.User user = new br.gov.frameworkdemoiselle.security.User() {

			private static final long serialVersionUID = 1L;

			private Map<Object, Object> attrs = new HashMap<Object, Object>();

			@Override
			public String getId() {
				return name;
			}

			@Override
			public Object getAttribute(Object key) {
				return this.attrs.get(key);
			}

			@Override
			public void setAttribute(Object key, Object value) {
				this.attrs.put(key, value);
			}

			@Override
			public String toString() {
				return this.getId();
			}
		};
		user.setAttribute("id", this.id);

		return user;
	}
	*/
}
