package example.twophasecommit.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Auditing implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String value;

	public Auditing() {
		super();
	}
	
	public Auditing(String value) {
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getValue() {
		return value;
	}

	
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
