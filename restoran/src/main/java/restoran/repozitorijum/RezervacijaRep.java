package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Rezervacija;

@Repository
public interface RezervacijaRep extends JpaRepository<Rezervacija, Long>{
	
	public Rezervacija findById(Long id);
}
