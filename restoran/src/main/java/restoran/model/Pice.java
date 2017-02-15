package restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pice {

	@Id
    @GeneratedValue
    private int id;
	    
	@Column(name="naziv", nullable = false)
	private String naziv;
	
	@Column(name="opis", nullable = false)
	private String opis;
	
	@Column(name="cena", nullable = false)
	private Double cena;
	
	@Column(name="ocena", nullable = false)
	private Double ocena;
	
	public Pice() {
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
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}
	
	
}