package restoran.model.osoba;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import restoran.logovanje.Korisnik;

@Entity
public class Sanker extends Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
}