package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.MenadzerRestorana;

@Repository
public interface MenadzerRestoranaRep extends JpaRepository<MenadzerRestorana, Long>{

	List<MenadzerRestorana> findById(Long id);
	
	public MenadzerRestorana findByMailAndPassword(String mail, String password);
}
