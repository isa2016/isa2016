package restoran.model.osoba;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import restoran.logovanje.Korisnik;

@Data
@Entity
public class Gost extends Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Gost_ID")
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gost() {
		super();
	}


	
}
