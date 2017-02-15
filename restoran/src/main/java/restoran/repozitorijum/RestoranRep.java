package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.Restoran;

@Repository
public interface RestoranRep extends JpaRepository<Restoran, Long>{
	
	List<Restoran> findById(Long id);
}
