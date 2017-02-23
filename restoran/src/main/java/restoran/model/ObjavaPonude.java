package restoran.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@ManyToMany
	@JoinTable(name = "Objava_ponude_pica", joinColumns = @JoinColumn(name = "Porudzbina_ID"), inverseJoinColumns = @JoinColumn(name = "Pice_ID"))
	private List<Pice> pice;

	@ManyToMany
	@JoinTable(name = "Objava_ponude_hrana", joinColumns = @JoinColumn(name = "Porudzbina_ID"), inverseJoinColumns = @JoinColumn(name = "Jelo_ID"))
	private List<Jelo> hrana;

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
	
	
	
}
