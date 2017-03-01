package restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PonudaP {

	@Id
	@GeneratedValue
	@Column(name = "PonudaP_ID")
	private Long id;
	
	@Column
	private String rokIsporuke;
	
	@Column
	private double cena;
	
	@Column
	private int garancija;
	
	private Long Ponudjac_Id;
	
	private Long Objava_ponude_Id;
	
	private String prihvati = "nije odluceno";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(String rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getGarancija() {
		return garancija;
	}

	public void setGarancija(int garancija) {
		this.garancija = garancija;
	}

	public PonudaP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getPonudjac_Id() {
		return Ponudjac_Id;
	}

	public void setPonudjac_Id(Long ponudjac_Id) {
		Ponudjac_Id = ponudjac_Id;
	}

	public Long getObjava_ponude_Id() {
		return Objava_ponude_Id;
	}

	public void setObjava_ponude_Id(Long objava_ponude_Id) {
		Objava_ponude_Id = objava_ponude_Id;
	}

	public String getPrihvati() {
		return prihvati;
	}

	public void setPrihvati(String prihvati) {
		this.prihvati = prihvati;
	}

	
	
}
