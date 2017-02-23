package restoran.servis;

import java.util.List;

import restoran.model.Porudzbina;

public interface PorudzbinaServis {
	List<Porudzbina> findAll();

	Porudzbina findOne(Long id);

	Porudzbina save(Porudzbina g);
	
	Porudzbina delete(Long id);
}
