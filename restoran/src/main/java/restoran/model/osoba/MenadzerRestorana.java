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
public class MenadzerRestorana extends Korisnik{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Menadzer_restorana_ID")
	private Long id;
	
	@Column
	private int zaposlen;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getZaposlen() {
		return zaposlen;
	}

	public void setZaposlen(int zaposlen) {
		this.zaposlen = zaposlen;
	}

	public MenadzerRestorana() {
		super();
		this.zaposlen = 0;
	}
	
	
	
	
}
