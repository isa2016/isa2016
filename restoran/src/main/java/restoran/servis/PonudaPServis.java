package restoran.servis;

import java.util.List;

import restoran.model.PonudaP;


public interface PonudaPServis {

	List<PonudaP> findAll();

	PonudaP findOne(Long id);

	PonudaP save(PonudaP g);
	
	PonudaP delete(Long id);
}
