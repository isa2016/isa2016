package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Pice;

@Repository
public interface PiceRep  extends JpaRepository<Pice, Long>{
	public Pice findById(Long id);

}
