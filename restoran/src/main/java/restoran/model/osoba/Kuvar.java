package restoran.model.osoba;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import restoran.enumeracije.Obuca;
import restoran.enumeracije.Odeca;
import restoran.logovanje.Korisnik;

@Data
@Entity
public class Kuvar extends Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Kuvar_ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private Odeca velicinaOdece;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private Obuca velicinaObuce;

	private Long restoranId;

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

	public Odeca getVelicinaOdece() {
		return velicinaOdece;
	}

	public void setVelicinaOdece(Odeca velicinaOdece) {
		this.velicinaOdece = velicinaOdece;
	}

	public Obuca getVelicinaObuce() {
		return velicinaObuce;
	}

	public void setVelicinaObuce(Obuca velicinaObuce) {
		this.velicinaObuce = velicinaObuce;
	}

}
