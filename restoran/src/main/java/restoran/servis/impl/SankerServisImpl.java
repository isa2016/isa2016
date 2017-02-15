package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.osoba.Sanker;
import restoran.repozitorijum.SankerRep;
import restoran.servis.SankerServis;

@Service
@Transactional
public class SankerServisImpl implements SankerServis{

	@Autowired
	private SankerRep sankerRep;
	
	@Override
	public List<Sanker> findAll() {
		// TODO Auto-generated method stub
		return sankerRep.findAll();
	}

	@Override
	public Sanker findOne(Long id) {
		// TODO Auto-generated method stub
		return sankerRep.findOne(id);
	}

	@Override
	public Sanker save(Sanker g) {
		// TODO Auto-generated method stub
		return sankerRep.save(g);
	}

	@Override
	public Sanker delete(Long id) {
		// TODO Auto-generated method stub
		Sanker sanker = sankerRep.findOne(id);
		if (sanker == null) {
			try {
				throw new Exception("Sanker nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sankerRep.delete(sanker);
		return sanker;
	}

	@Override
	public Sanker findByMailAndPassword(String usn, String pass) {
		// TODO Auto-generated method stub
		return sankerRep.findByMailAndPassword(usn, pass);
	}

}
