package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.Gost;
import restoran.model.osoba.Ponudjac;
import restoran.repozitorijum.PonudjacRep;
import restoran.servis.PonudjacServis;


@Service
@Transactional
public class PonudjacServisImpl implements PonudjacServis {

	
	@Autowired
	private PonudjacRep ponudjacRep;
	
	
	@Override
	public List<Ponudjac> findAll() {
		// TODO Auto-generated method stub
		return ponudjacRep.findAll();
	}

	@Override
	public Ponudjac findOne(Long id) {
		// TODO Auto-generated method stub
		return ponudjacRep.findOne(id);
	}

	@Override
	public Ponudjac save(Ponudjac g) {
		// TODO Auto-generated method stub
		return ponudjacRep.save(g);
	}

	@Override
	public Ponudjac delete(Long id) {
		// TODO Auto-generated method stub
		Ponudjac ponudjac = ponudjacRep.findOne(id);
		if (ponudjac == null) {
			try {
				throw new Exception("Ponudjac nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ponudjacRep.delete(ponudjac);
		return ponudjac;
	}

	@Override
	public Ponudjac findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return ponudjacRep.findByMailAndPassword(usn, pass);
	}

	

}
