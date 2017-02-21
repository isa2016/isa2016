package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.MenadzerSistema;

@Repository
public interface MenadzerSistemaRep extends JpaRepository<MenadzerSistema, Long>{

	List<MenadzerSistema> findById(Long id);
	
	public MenadzerSistema findByMailAndPassword(String mail, String password);

	MenadzerSistema findByMail(String mail);
}
