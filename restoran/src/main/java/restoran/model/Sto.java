package restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
public class Sto {

	
	@Id
	@GeneratedValue
	@Column(name = "Sto_ID")
	private Long id;
	
	@Column
	private String naziv;
	
	@Version
	@Column
	private Integer version;
	
	@Column
	private Long rejon;
	
	@Column 
	private int vrsta;
	
	@Column int kolona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getRejon() {
		return rejon;
	}

	public void setRejon(Long rejon) {
		this.rejon = rejon;
	}

	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Sto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getVrsta() {
		return vrsta;
	}

	public void setVrsta(int vrsta) {
		this.vrsta = vrsta;
	}

	public int getKolona() {
		return kolona;
	}

	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
	
	
	
}
