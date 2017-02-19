package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;

@Repository
public interface RezervacijaRep extends JpaRepository<Rezervacija, Long>{
	
	public Gost findById(Long id);
}
