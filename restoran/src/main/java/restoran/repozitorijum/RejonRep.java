package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Rejon;

@Repository
public interface RejonRep extends JpaRepository<Rejon, Long>{

	public Rejon findById(Long id);
}
