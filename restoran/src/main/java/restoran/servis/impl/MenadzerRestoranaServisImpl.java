package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.MenadzerRestorana;
import restoran.repozitorijum.MenadzerRestoranaRep;
import restoran.servis.MenadzerRestoranaServis;

@Service
@Transactional
public class MenadzerRestoranaServisImpl implements MenadzerRestoranaServis{

	@Autowired
	private MenadzerRestoranaRep menRep;

	@Override
	public List<MenadzerRestorana> findAll() {
		// TODO Auto-generated method stub
		return menRep.findAll();
	}

	@Override
	public MenadzerRestorana findOne(Long id) {
		// TODO Auto-generated method stub
		return menRep.findOne(id);
	}

	@Override
	public MenadzerRestorana save(MenadzerRestorana g) {
		// TODO Auto-generated method stub
		return menRep.save(g);
	}

	@Override
	public MenadzerRestorana delete(Long id) {
		// TODO Auto-generated method stub
		MenadzerRestorana men = menRep.findOne(id);
		if (men == null) {
			try {
				throw new Exception("Menadzer restorana nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		menRep.delete(men);
		return men;
	}

	@Override
	public MenadzerRestorana findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return menRep.findByMailAndPassword(usn, pass);
	}
	
}
