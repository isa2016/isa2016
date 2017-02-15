package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Gost;

@Repository
public interface GostRep extends JpaRepository<Gost, Long> {
	public Gost findById(Long id);

	public Gost findByMailAndPassword(String mail, String password);
}
