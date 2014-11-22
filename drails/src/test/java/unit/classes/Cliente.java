package unit.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
	
	
	private static final long serialVersionUID = 1L;
	
	/*
	 *  If you are using Glassfish then remove the strategy attribute
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String description;
	
	@Column
	private String link; 
	
	@OneToMany
	private List<Produto> produtos;
	

}
