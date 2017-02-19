package restoran.servis;

import java.util.List;

import restoran.model.osoba.MenadzerSistema;

public interface MenadzerSistemaServis {
	
	List<MenadzerSistema> findAll();

	MenadzerSistema findOne(Long id);

	MenadzerSistema save(MenadzerSistema g);

	MenadzerSistema delete(Long id);

	MenadzerSistema findByMailAndPassword(String usn, String pass);
	
}
