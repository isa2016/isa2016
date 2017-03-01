package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import restoran.model.PonudaP;

@Repository
public interface PonudaPRep extends JpaRepository<PonudaP, Long> {

	public PonudaP findById(Long id);
}
