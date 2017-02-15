package restoran.servis;

import java.util.List;

import restoran.model.osoba.MenadzerRestorana;

public interface MenadzerRestoranaServis {

	List<MenadzerRestorana> findAll();

	MenadzerRestorana findOne(Long id);

	MenadzerRestorana save(MenadzerRestorana g);

	MenadzerRestorana delete(Long id);

	MenadzerRestorana findByMailAndPassword(String usn, String pass);
}
