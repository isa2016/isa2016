package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;

import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;

public interface RezervacijaRep extends JpaRepository<Rezervacija, Long>{
	
	public Gost findById(Long id);
}
