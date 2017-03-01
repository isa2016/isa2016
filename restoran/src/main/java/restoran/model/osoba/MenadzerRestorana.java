package restoran.model.osoba;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import restoran.logovanje.Korisnik;
import restoran.model.PonudaP;

@Data
@Entity
public class MenadzerRestorana extends Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Menadzer_restorana_ID")
	private Long id;

	@Column
	private int zaposlen;

	private Long restoranId;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Menadzeri_i_ponude", joinColumns = @JoinColumn(name = "Menadzer_restorana_ID"), inverseJoinColumns = @JoinColumn(name = "PonudaP_ID"))
	private List<PonudaP> ponudaP;
	
	public Long getRestoranId() {
		return restoranId;
	}

	public void setRestoranId(Long restoranId) {
		this.restoranId = restoranId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getZaposlen() {
		return zaposlen;
	}

	public void setZaposlen(int zaposlen) {
		this.zaposlen = zaposlen;
	}

	public MenadzerRestorana() {
		super();
		this.zaposlen = 0;
	}

	public List<PonudaP> getPonudaP() {
		return ponudaP;
	}

	public void setPonudaP(List<PonudaP> ponudaP) {
		this.ponudaP = ponudaP;
	}

	
}
