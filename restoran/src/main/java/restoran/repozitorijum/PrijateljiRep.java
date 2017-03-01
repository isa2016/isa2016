package restoran.repozitorijum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Gost;
import restoran.model.osoba.Prijatelji;

@Repository
public interface PrijateljiRep extends JpaRepository<Prijatelji, Long> {
	
	public Prijatelji findById(Long id);
	
	public List<Prijatelji> findByPrimioZahtev(Gost gost);
	
	public List<Prijatelji> findByPoslaoZahtev(Gost gost);
	
	public Prijatelji findByPrimioZahtevAndPoslaoZahtev(Gost gost,Gost gost2);
}
