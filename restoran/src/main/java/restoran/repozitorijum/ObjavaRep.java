package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.ObjavaPonude;
import restoran.model.Rezervacija;

@Repository
public interface ObjavaRep extends JpaRepository<ObjavaPonude, Long>{

	public ObjavaPonude findById(Long id);
	
}
