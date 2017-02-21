package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Sanker;

@Repository
public interface SankerRep extends JpaRepository<Sanker, Long>{

		List<Sanker> findById(Long id);
		
		public Sanker findByMailAndPassword(String mail, String password);

		Sanker findByMail(String mail);
}
