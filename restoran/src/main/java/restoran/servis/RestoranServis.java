package restoran.servis;

import java.util.List;

import restoran.model.Restoran;

public interface RestoranServis {

	List<Restoran> findAll();

	Restoran findOne(Long id);

	Restoran save(Restoran g);

	Restoran delete(Long id);
}
