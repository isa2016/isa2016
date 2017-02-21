package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Kuvar;

@Repository
public interface KuvarRep extends JpaRepository<Kuvar, Long>{

	List<Kuvar> findById(Long id);
	
	public Kuvar findByMailAndPassword(String mail, String password);

	Kuvar findByMail(String mail);
}
