package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Jelo;
import restoran.repozitorijum.JeloRep;
import restoran.servis.JeloServis;

@Service
@Transactional
public class JeloServisImpl implements JeloServis {

	@Autowired
	private JeloRep JeloRep;

	@Override
	public List<Jelo> findAll() {
		return JeloRep.findAll();
	}

	@Override
	public Jelo findOne(Long id) {
		// TODO Auto-generated method stub
		return JeloRep.findOne(id);
	}

	@Override
	public Jelo save(Jelo g) {
		// TODO Auto-generated method stub
		return JeloRep.save(g);
	}

	@Override
	public Jelo delete(Long id) {
		// TODO Auto-generated method stub
		Jelo Jelo = JeloRep.findOne(id);
		if (Jelo == null) {
			try {
				throw new Exception("Jelo nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JeloRep.delete(Jelo);
		return Jelo;
	}



}
