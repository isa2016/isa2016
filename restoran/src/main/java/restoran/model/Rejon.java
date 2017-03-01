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
	private String naziv;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Rejon(String naziv) {
		super();
		this.naziv = naziv;
		// TODO Auto-generated constructor stub
	}
	
	
}
