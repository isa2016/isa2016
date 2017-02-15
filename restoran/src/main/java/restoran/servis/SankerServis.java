package restoran.servis;

import java.util.List;

import restoran.model.osoba.Sanker;

public interface SankerServis {
	List<Sanker> findAll();

	Sanker findOne(Long id);

	Sanker save(Sanker g);

	Sanker delete(Long id);

	Sanker findByMailAndPassword(String usn, String pass);
}
