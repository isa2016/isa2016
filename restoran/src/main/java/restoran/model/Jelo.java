package restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import restoran.enumeracije.StatusJela;
import restoran.enumeracije.TipJela;

@Data
@Entity
public class Jelo {

	@Id
	@GeneratedValue
	@Column(name = "Jelo_ID")
	private Long id;

	@Column(name = "naziv", nullable = false)
	private String naziv;

	@Column(name = "opis", nullable = false)
	private String opis;

	@Column(name = "cena", nullable = false)
	private Double cena;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private TipJela tipJela;

	@Enumerated(EnumType.STRING)
	private StatusJela statusJela;
	
	public StatusJela getStatusJela() {
		return statusJela;
	}

	public void setStatusJela(StatusJela statusJela) {
		this.statusJela = statusJela;
	}

	@Column(name = "ocena", nullable = true)
	private Double ocena;

	@Column
	public int brojac;

	public Jelo() {
		super();
	}

	public TipJela getTipJela() {
		return tipJela;
	}

	public void setTipJela(TipJela tipJela) {
		this.tipJela = tipJela;
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

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojac() {
		return brojac;
	}

	public void setBrojac(int brojac) {
		this.brojac = brojac;
	}

}
