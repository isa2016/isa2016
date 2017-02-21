package restoran.servis;

import java.util.List;

import restoran.model.osoba.Kuvar;

public interface KuvarServis {
	List<Kuvar> findAll();

	Kuvar findOne(Long id);

	Kuvar save(Kuvar g);

	Kuvar delete(Long id);

	Kuvar findByMailAndPassword(String usn, String pass);

	Kuvar findByMail(String mail);
}
