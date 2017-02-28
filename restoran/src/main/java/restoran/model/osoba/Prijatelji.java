package restoran.model.osoba;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import restoran.enumeracije.PrijateljstvoStatus;

@Data
@Entity
public class Prijatelji {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Prijateljstvo_ID")
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Poslao_zahtev")
	private Gost poslaoZahtev;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Primio_zahtev")
	private Gost primioZahtev;
	
	@Enumerated(EnumType.STRING)
	@Column
	private PrijateljstvoStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Gost getPoslaoZahtev() {
		return poslaoZahtev;
	}

	public void setPoslaoZahtev(Gost poslaoZahtev) {
		this.poslaoZahtev = poslaoZahtev;
	}

	public Gost getPrimioZahtev() {
		return primioZahtev;
	}

	public void setPrimioZahtev(Gost primioZahtev) {
		this.primioZahtev = primioZahtev;
	}

	public PrijateljstvoStatus getStatus() {
		return status;
	}

	public void setStatus(PrijateljstvoStatus status) {
		this.status = status;
	}

	public Prijatelji(Gost poslaoZahtev, Gost primioZahtev, PrijateljstvoStatus status) {
		super();
		this.poslaoZahtev = poslaoZahtev;
		this.primioZahtev = primioZahtev;
		this.status = status;
	}

	public Prijatelji() {
		super();
	}
	
	
	
}
