package restoran.servis;

import java.util.List;

import restoran.model.ObjavaPonude;



public interface ObjavaPonudeServis {

	List<ObjavaPonude> findAll();

	ObjavaPonude findOne(Long id);
	
	ObjavaPonude save(ObjavaPonude g);

	ObjavaPonude delete(Long id);
}
