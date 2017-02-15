package restoran.servis;

import java.util.List;

import restoran.model.osoba.Ponudjac;

public interface PonudjacServis {
	List<Ponudjac> findAll();

	Ponudjac findOne(Long id);

	Ponudjac save(Ponudjac g);

	Ponudjac delete(Long id);

	Ponudjac findByMailAndPassword(String usn, String pass);
}
