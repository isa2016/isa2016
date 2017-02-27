package restoran.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.MenadzerRestorana;
import restoran.model.osoba.Ponudjac;
import restoran.model.osoba.Sanker;

@Data
@Entity 
public class Restoran {
	
	@Id
    @GeneratedValue
    @Column(name = "Restoran_ID")
	private Long id;
	
	@Column(name="naziv", nullable = false)
	public String naziv;
	
	@Column(name="opis", nullable = false)
	public String opis;
	
	@Column
	private String drzava;

	@Column
	private String grad;
	
	@Column
	private String ulica;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Restorani_i_menadzeri", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Menadzer_restorana_ID"))
	private List<MenadzerRestorana> menadzeriRestorana;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Jelovnik", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Jelo_ID"))
	public List<Jelo> jelovnik = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Karta_pica", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Pice_ID"))
	public List<Pice> kartaPica = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Restorani_i_konobari", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Konobar_ID"))
	public List<Konobar> konobari = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Restorani_i_kuvari", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Kuvar_ID"))
	public List<Kuvar> kuvari = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Restorani_i_sankeri", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Sanker_ID"))
	public List<Sanker> sankeri = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "Restorani_i_ponudjaci", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Ponudjac_ID"))
	private List<Ponudjac> ponudjaci;
	
	public List<MenadzerRestorana> getMenadzeriRestorana() {
		return menadzeriRestorana;
	}

	public void setMenadzeriRestorana(List<MenadzerRestorana> menadzeriRestorana) {
		this.menadzeriRestorana = menadzeriRestorana;
	}

	public Restoran() {
		super();
		this.menadzeriRestorana = new ArrayList<MenadzerRestorana>();
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

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Jelo> getJelovnik() {
		return jelovnik;
	}

	public void setJelovnik(List<Jelo> jelovnik) {
		this.jelovnik = jelovnik;
	}

	public List<Pice> getKartaPica() {
		return kartaPica;
	}

	public void setKartaPica(List<Pice> kartaPica) {
		this.kartaPica = kartaPica;
	}

	public List<Konobar> getKonobari() {
		return konobari;
	}

	public void setKonobari(List<Konobar> konobari) {
		this.konobari = konobari;
	}

	public List<Kuvar> getKuvari() {
		return kuvari;
	}

	public void setKuvari(List<Kuvar> kuvari) {
		this.kuvari = kuvari;
	}

	public List<Sanker> getSankeri() {
		return sankeri;
	}

	public void setSankeri(List<Sanker> sankeri) {
		this.sankeri = sankeri;
	}

	public List<Ponudjac> getPonudjaci() {
		return ponudjaci;
	}

	public void setPonudjaci(List<Ponudjac> ponudjaci) {
		this.ponudjaci = ponudjaci;
	}

}
