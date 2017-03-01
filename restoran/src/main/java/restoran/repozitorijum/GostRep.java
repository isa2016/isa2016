package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Gost;

@Repository
public interface GostRep extends JpaRepository<Gost, Long> {
	public Gost findById(Long id);

	public Gost findByMailAndPassword(String mail, String password);
	
	public Gost findByRegistrovan(String registrovan);
	
	public List<Gost> findByIme(String ime);
	
	public List<Gost> findByPrezime(String prezime);
	
	public List<Gost> findByImeAndPrezime(String ime,String prezime);
}
