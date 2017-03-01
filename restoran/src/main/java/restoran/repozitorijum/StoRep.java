package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Sto;

@Repository
public interface StoRep extends JpaRepository<Sto, Long>{
	
	public Sto findById(Long id);
}
