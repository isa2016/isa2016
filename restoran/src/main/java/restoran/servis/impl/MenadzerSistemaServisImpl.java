package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.MenadzerSistema;
import restoran.repozitorijum.MenadzerSistemaRep;
import restoran.servis.MenadzerSistemaServis;

@Service
@Transactional
public class MenadzerSistemaServisImpl implements MenadzerSistemaServis{

	@Autowired
	private MenadzerSistemaRep menRep;
	
	@Override
	public List<MenadzerSistema> findAll() {
		// TODO Auto-generated method stub
		return menRep.findAll();
	}

	@Override
	public MenadzerSistema findOne(Long id) {
		// TODO Auto-generated method stub
		return menRep.findOne(id);
	}

	@Override
	public MenadzerSistema save(MenadzerSistema g) {
		// TODO Auto-generated method stub
		return menRep.save(g);
	}

	@Override
	public MenadzerSistema delete(Long id) {
		// TODO Auto-generated method stub
		MenadzerSistema men = menRep.findOne(id);
		if (men == null) {
			try {
				throw new Exception("Menadzer sistema nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		menRep.delete(men);
		return men;
	}

	@Override
	public MenadzerSistema findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return menRep.findByMailAndPassword(usn, pass);
	}

	@Override
	public MenadzerSistema findByMail(String mail) {
		// TODO Auto-generated method stub
		return menRep.findByMail(mail);
	}

}
