package restoran.servis;

import java.util.List;

import restoran.model.osoba.Gost;

public interface GostServis {

	List<Gost> findAll();

	Gost findOne(Long id);

	Gost save(Gost g);

	Gost delete(Long id);

	Gost findByMailAndPassword(String usn, String pass);

	void aktiviraj(String broj);
}
