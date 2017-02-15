package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Ponudjac;

@Repository
public interface PonudjacRep extends JpaRepository<Ponudjac, Long> {

	List<Ponudjac> findById(Long id);
	
	public Ponudjac findByMailAndPassword(String mail, String password);
}
