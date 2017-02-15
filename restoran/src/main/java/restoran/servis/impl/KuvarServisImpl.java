package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restoran.model.osoba.Kuvar;
import restoran.repozitorijum.KuvarRep;
import restoran.servis.KuvarServis;

@Service
@Transactional
public class KuvarServisImpl implements KuvarServis{

	@Autowired
	private KuvarRep kuvarRep;
	
	
	@Override
	public List<Kuvar> findAll() {
		// TODO Auto-generated method stub
		return kuvarRep.findAll();
	}

	@Override
	public Kuvar findOne(Long id) {
		// TODO Auto-generated method stub
		return kuvarRep.findOne(id);
	}

	@Override
	public Kuvar save(Kuvar g) {
		// TODO Auto-generated method stub
		return kuvarRep.save(g);
	}

	@Override
	public Kuvar delete(Long id) {
		// TODO Auto-generated method stub
		Kuvar kuvar = kuvarRep.findOne(id);
		if (kuvar == null) {
			try {
				throw new Exception("Kuvar nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		kuvarRep.delete(kuvar);
		return kuvar;
	}

	@Override
	public Kuvar findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return kuvarRep.findByMailAndPassword(usn, pass);
	}

}
