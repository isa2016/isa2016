package restoran.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import restoran.enumeracije.HranaStatus;
import restoran.enumeracije.PiceStatus;
import restoran.enumeracije.PorudzbinaStatus;
import restoran.model.osoba.Gost;

@Data
@Entity
public class Porudzbina {

	public Porudzbina() {
		this.pice = new ArrayList<Pice>();
		this.hrana = new ArrayList<Jelo>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Porudzbina_ID")
	private Long id;

	@ManyToOne
	@JoinTable(name = "Porudzbina_i_gost", joinColumns = @JoinColumn(name = "Porudzbina_ID"), inverseJoinColumns = @JoinColumn(name = "Gost_ID"))
	private Gost gost;

	@ManyToMany
	@JoinTable(name = "Porudzbina_pica", joinColumns = @JoinColumn(name = "Porudzbina_ID"), inverseJoinColumns = @JoinColumn(name = "Pice_ID"))
	private List<Pice> pice;

	@Column
	private double ukupnaCena;

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	@ManyToMany
	@JoinTable(name = "Porudzbina_hrana", joinColumns = @JoinColumn(name = "Porudzbina_ID"), inverseJoinColumns = @JoinColumn(name = "Jelo_ID"))
	private List<Jelo> hrana;

	@Enumerated(EnumType.STRING)
	@Column
	private HranaStatus hranaStatus;

	@Enumerated(EnumType.STRING)
	@Column
	private PorudzbinaStatus porudzbinaStatus;

	@Enumerated(EnumType.STRING)
	@Column
	private PiceStatus piceStatus;

	private Long restoranId;

	@Column
	private String datum;

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public PorudzbinaStatus getPorudzbinaStatus() {
		return porudzbinaStatus;
	}

	public void setPorudzbinaStatus(PorudzbinaStatus porudzbinaStatus) {
		this.porudzbinaStatus = porudzbinaStatus;
	}

	public Long getRestoranId() {
		return restoranId;
	}

	public void setRestoranId(Long restoranId) {
		this.restoranId = restoranId;
	}

	public HranaStatus getHranaStatus() {
		return hranaStatus;
	}

	public void setHranaStatus(HranaStatus hranaStatus) {
		this.hranaStatus = hranaStatus;
	}

	public PiceStatus getPiceStatus() {
		return piceStatus;
	}

	public void setPiceStatus(PiceStatus piceStatus) {
		this.piceStatus = piceStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gost getGost() {
		return gost;
	}

	public void setGost(Gost gost) {
		this.gost = gost;
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

}
