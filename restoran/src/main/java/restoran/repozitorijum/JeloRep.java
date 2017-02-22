package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Jelo;

@Repository
public interface JeloRep extends JpaRepository<Jelo, Long>{
	public Jelo findById(Long id);
}
