package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.Gost;
import restoran.model.osoba.Prijatelji;
import restoran.repozitorijum.PrijateljiRep;
import restoran.servis.PrijateljiServis;

@Service
@Transactional
public class PrijateljiServisImpl implements PrijateljiServis{
	
	
	@Autowired
	private PrijateljiRep PrijateljiRep;

	@Override
	public List<Prijatelji> findAll() {
		return PrijateljiRep.findAll();
	}

	@Override
	public Prijatelji findOne(Long id) {
		// TODO Auto-generated method stub
		return PrijateljiRep.findOne(id);
	}

	@Override
	public Prijatelji save(Prijatelji g) {
		// TODO Auto-generated method stub
		return PrijateljiRep.save(g);
	}

	@Override
	public Prijatelji delete(Long id) {
		// TODO Auto-generated method stub
		Prijatelji Prijatelji = PrijateljiRep.findOne(id);
		if (Prijatelji == null) {
			try {
				throw new Exception("Prijatelji nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PrijateljiRep.delete(Prijatelji);
		return Prijatelji;
	}

	@Override
	public List<Prijatelji> findByPrimioZahtev(Gost gost) {
		// TODO Auto-generated method stub
		return PrijateljiRep.findByPrimioZahtev(gost);
	}

	@Override
	public Prijatelji findByPrimioZahtevAndPoslaoZahtev(Gost gost, Gost gost2) {
		// TODO Auto-generated method stub
		return PrijateljiRep.findByPrimioZahtevAndPoslaoZahtev(gost, gost2);
	}

	@Override
	public List<Prijatelji> findByPoslaoZahtev(Gost gost) {
		// TODO Auto-generated method stub
		return PrijateljiRep.findByPoslaoZahtev(gost);
	}
}
