package restoran.servis;

import java.util.List;

import restoran.model.Sto;

public interface StoServis {

	List<Sto> findAll();

	Sto findOne(Long id);

	Sto save(Sto g);

	Sto delete(Long id);
}
