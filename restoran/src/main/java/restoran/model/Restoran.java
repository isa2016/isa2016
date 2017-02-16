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
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.MenadzerRestorana;
import restoran.model.osoba.Sanker;

@Data
@Entity 
public class Restoran {
	
	@Id
    @GeneratedValue
    @Column(name = "Restoran_ID")
	private int id;
	
	@Column(name="naziv", nullable = false)
	public String naziv;
	
	@Column(name="opis", nullable = false)
	public String opis;
	
	@Column
	@NotBlank
	private String drzava;

	@Column
	@NotBlank
	private String grad;
	
	@Column
	@NotBlank
	private String ulica;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Restorani_i_menadzeri", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Menadzer_restorana_ID"))
	private List<MenadzerRestorana> menadzeriRestorana;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Jelovnik", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Jelo_ID"))
	public List<Jelo> jelovnik = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Restorani_i_menadzeri", joinColumns = @JoinColumn(name = "Restoran_ID"), inverseJoinColumns = @JoinColumn(name = "Pice_ID"))
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



	
	

}
