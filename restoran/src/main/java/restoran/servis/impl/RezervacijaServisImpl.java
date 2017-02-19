package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Rezervacija;
import restoran.repozitorijum.RezervacijaRep;
import restoran.servis.RezervacijaServis;

@Service
@Transactional
public class RezervacijaServisImpl implements RezervacijaServis{

	@Autowired
	private RezervacijaRep RezervacijaRep;

	@Override
	public List<Rezervacija> findAll() {
		// TODO Auto-generated method stub
		return RezervacijaRep.findAll();
	}

	@Override
	public Rezervacija findOne(Long id) {
		// TODO Auto-generated method stub
		return RezervacijaRep.findOne(id);
	}

	@Override
	public Rezervacija save(Rezervacija g) {
		// TODO Auto-generated method stub
		return RezervacijaRep.save(g);
	}

	@Override
	public Rezervacija delete(Long id) {
		// TODO Auto-generated method stub
		Rezervacija Rezervacija = RezervacijaRep.findOne(id);
		if (Rezervacija == null) {
			try {
				throw new Exception("Rezervacija nije pronadjena");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RezervacijaRep.delete(Rezervacija);
		return Rezervacija;
	}
}
