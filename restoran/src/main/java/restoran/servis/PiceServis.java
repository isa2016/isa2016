package restoran.servis;

import java.util.List;

import restoran.model.Pice;

public interface PiceServis {

	List<Pice> findAll();

	Pice findOne(Long id);

	Pice save(Pice g);

	Pice delete(Long id);
}
