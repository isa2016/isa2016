package restoran.servis;

import java.util.List;

import restoran.model.osoba.Gost;

public interface GostServis {

	List<Gost> findAll();

	Gost findOne(Long id);

	Gost save(Gost g);

	Gost delete(Long id);

	Gost findByMailAndPassword(String usn, String pass);
	
	List<Gost> findByIme(String ime);
	
	List<Gost> findByPrezime(String prezime);
	
	List<Gost> findByImeAndPrezime(String ime,String prezime);

	void aktiviraj(String broj);
}
