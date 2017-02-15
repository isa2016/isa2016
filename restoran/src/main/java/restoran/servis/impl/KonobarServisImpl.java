package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.Konobar;
import restoran.repozitorijum.KonobarRep;
import restoran.servis.KonobarServis;

@Service
@Transactional
public class KonobarServisImpl implements KonobarServis{

	@Autowired
	private KonobarRep konobarRep;
	
	@Override
	public List<Konobar> findAll() {
		// TODO Auto-generated method stub
		return konobarRep.findAll();
	}

	@Override
	public Konobar findOne(Long id) {
		// TODO Auto-generated method stub
		return konobarRep.findOne(id);
	}

	@Override
	public Konobar save(Konobar g) {
		// TODO Auto-generated method stub
		return konobarRep.save(g);
	}

	@Override
	public Konobar delete(Long id) {
		// TODO Auto-generated method stub
		Konobar k = konobarRep.findOne(id);
		if (k == null) {
			try {
				throw new Exception("Konobar nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		konobarRep.delete(k);
		return k;
	}

	@Override
	public Konobar findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return konobarRep.findByMailAndPassword(usn, pass);
	}

}
