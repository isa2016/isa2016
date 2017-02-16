package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.Gost;
import restoran.repozitorijum.GostRep;
import restoran.servis.GostServis;

@Service
@Transactional
public class GostServisImpl implements GostServis {

	@Autowired
	private GostRep gostRep;

	@Override
	public List<Gost> findAll() {
		// TODO Auto-generated method stub
		return gostRep.findAll();
	}

	@Override
	public Gost findOne(Long id) {
		// TODO Auto-generated method stub
		return gostRep.findOne(id);
	}

	@Override
	public Gost save(Gost g) {
		// TODO Auto-generated method stub
		return gostRep.save(g);
	}

	@Override
	public Gost delete(Long id) {
		// TODO Auto-generated method stub
		Gost gost = gostRep.findOne(id);
		if (gost == null) {
			try {
				throw new Exception("Gost nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		gostRep.delete(gost);
		return gost;
	}

	@Override
	public Gost findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return gostRep.findByMailAndPassword(usn, pass);
	}

	@Override
	public void aktiviraj(String broj) {
		// TODO Auto-generated method stub
		Gost gost = gostRep.findById(Long.valueOf(broj).longValue());
		gost.setRegistrovan("1");
	}

}
