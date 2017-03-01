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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import restoran.logovanje.Korisnik;
import restoran.model.ObjavaPonude;
import restoran.model.PonudaP;

@Data
@Entity
public class Ponudjac extends Korisnik{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Ponudjac_ID")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
    @ManyToMany
	@JoinTable(name = "Objave_i_ponudjaci", joinColumns = @JoinColumn(name = "Objava_ponude_ID"), inverseJoinColumns = @JoinColumn(name = "Ponudjac_ID"))
	private List<ObjavaPonude> objave;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Ponude_i_ponudjaci", joinColumns = @JoinColumn(name = "Ponudjac_ID"), inverseJoinColumns = @JoinColumn(name = "PonudaP_ID"))
	private List<PonudaP> ponuda;
    
	public List<ObjavaPonude> getObjave() {
		return objave;
	}

	public void setObjave(List<ObjavaPonude> objave) {
		this.objave = objave;
	}

	public List<PonudaP> getPonuda() {
		return ponuda;
	}

	public void setPonuda(List<PonudaP> ponuda) {
		this.ponuda = ponuda;
	}
	
	
}
