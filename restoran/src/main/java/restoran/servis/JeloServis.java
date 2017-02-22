package restoran.servis;

import java.util.List;

import restoran.model.Jelo;

public interface JeloServis {

	List<Jelo> findAll();

	Jelo findOne(Long id);

	Jelo save(Jelo g);

	Jelo delete(Long id);
}
