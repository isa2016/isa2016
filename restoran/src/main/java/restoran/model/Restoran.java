package restoran.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Restoran {
	
	@Id
    @GeneratedValue
	private int id;
	
	@Column(name="naziv", nullable = false)
	public String naziv;
	
	@Column(name="opis", nullable = false)
	public String opis;
	
	public ArrayList<Jelo> jelovnik = new ArrayList<>();
	public ArrayList<Pice> kartaPica = new ArrayList<>();
	
	public Restoran() {
		super();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Jelo> getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(ArrayList<Jelo> jelovnik) {
		this.jelovnik = jelovnik;
	}

	public ArrayList<Pice> getKartaPica() {
		return kartaPica;
	}

	public void setKartaPica(ArrayList<Pice> kartaPica) {
		this.kartaPica = kartaPica;
	}
	
	

}
