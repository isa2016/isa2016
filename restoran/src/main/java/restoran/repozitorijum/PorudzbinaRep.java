package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Porudzbina;

@Repository
public interface PorudzbinaRep extends JpaRepository<Porudzbina, Long>{
	public Porudzbina findById(Long id);
}
