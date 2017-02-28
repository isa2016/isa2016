package restoran.servis;

import java.util.List;

import restoran.model.osoba.Gost;
import restoran.model.osoba.Prijatelji;

public interface PrijateljiServis {

	List<Prijatelji> findAll();

	Prijatelji findOne(Long id);

	Prijatelji save(Prijatelji g);

	Prijatelji delete(Long id);
	
	List<Prijatelji> findByPrimioZahtev(Gost gost);
	
	List<Prijatelji> findByPoslaoZahtev(Gost gost);
	
	Prijatelji findByPrimioZahtevAndPoslaoZahtev(Gost gost,Gost gost2);
}
