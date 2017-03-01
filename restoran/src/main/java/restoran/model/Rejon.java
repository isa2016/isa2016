package restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Rejon {

	
	@Id
	@GeneratedValue
	@Column(name = "Rejon_ID")
	private Long id;
	
	@Column
	private int broj;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    
	

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public Rejon(int broj) {
		super();
		this.broj = broj;
		// TODO Auto-generated constructor stub
	}
	
	
}
