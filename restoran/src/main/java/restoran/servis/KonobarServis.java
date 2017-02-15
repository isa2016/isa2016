package restoran.servis;

import java.util.List;

import restoran.model.osoba.Konobar;

public interface KonobarServis {

	List<Konobar> findAll();

	Konobar findOne(Long id);

	Konobar save(Konobar g);

	Konobar delete(Long id);

	Konobar findByMailAndPassword(String usn, String pass);
}
