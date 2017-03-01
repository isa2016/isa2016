package restoran.servis;

import java.util.List;

import restoran.model.Rejon;

public interface RejonServis {

	
	List<Rejon> findAll();

	Rejon findOne(Long id);

	Rejon save(Rejon g);

	Rejon delete(Long id);
}
