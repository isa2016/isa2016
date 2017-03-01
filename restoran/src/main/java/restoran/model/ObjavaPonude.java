package restoran.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class ObjavaPonude {

	
	@Id
	@GeneratedValue
	@Column(name = "Objava_ponude_ID")
	private Long id;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Objava_ponude_pica", joinColumns = @JoinColumn(name = "Objava_ponude_ID"), inverseJoinColumns = @JoinColumn(name = "Pice_ID"))
	private List<Pice> pice;

	@ManyToMany
	@JoinTable(name = "Objava_ponude_hrana", joinColumns = @JoinColumn(name = "Objava_ponude_ID"), inverseJoinColumns = @JoinColumn(name = "Jelo_ID"))
	private List<Jelo> hrana;

	@Column
	private String pocetakVazenja;
	
	@Column
	private String krajaVazenja;
	
	private Long restoranID;
	
	
	
	public Long getRestoranID() {
		return restoranID;
	}

	public void setRestoranID(Long restoranID) {
		this.restoranID = restoranID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pice> getPice() {
		return pice;
	}

	public void setPice(List<Pice> pice) {
		this.pice = pice;
	}

	public List<Jelo> getHrana() {
		return hrana;
	}

	public void setHrana(List<Jelo> hrana) {
		this.hrana = hrana;
	}

	public ObjavaPonude() {
		super();
		// TODO Auto-generated constructor stub
		this.hrana = new ArrayList<Jelo>();
        this.pice = new ArrayList<Pice>();	
	}

	public String getPocetakVazenja() {
		return pocetakVazenja;
	}

	public void setPocetakVazenja(String pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}

	public String getKrajaVazenja() {
		return krajaVazenja;
	}

	public void setKrajaVazenja(String krajaVazenja) {
		this.krajaVazenja = krajaVazenja;
	}
	
	
	
}
