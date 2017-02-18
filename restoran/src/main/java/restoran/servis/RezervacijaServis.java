package restoran.servis;

import java.util.List;

import restoran.model.Rezervacija;

public interface RezervacijaServis {

	List<Rezervacija> findAll();

	Rezervacija findOne(Long id);
	
	Rezervacija save(Rezervacija g);

	Rezervacija delete(Long id);
}
