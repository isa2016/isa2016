package restoran.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import restoran.model.osoba.Gost;

@Data
@Entity
public class Porudzbina {
	
	
	public Porudzbina(){
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

	@ManyToMany
	@JoinTable(name = "Porudzbina_hrana", joinColumns = @JoinColumn(name = "Porudzbina_ID"), inverseJoinColumns = @JoinColumn(name = "Jelo_ID"))
	private List<Jelo> hrana;

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