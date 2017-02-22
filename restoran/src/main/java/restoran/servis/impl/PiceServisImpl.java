package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Pice;
import restoran.repozitorijum.PiceRep;
import restoran.servis.PiceServis;

@Service
@Transactional
public class PiceServisImpl implements PiceServis{

	
	@Autowired
	private PiceRep PiceRep;

	@Override
	public List<Pice> findAll() {
		return PiceRep.findAll();
	}

	@Override
	public Pice findOne(Long id) {
		// TODO Auto-generated method stub
		return PiceRep.findOne(id);
	}

	@Override
	public Pice save(Pice g) {
		// TODO Auto-generated method stub
		return PiceRep.save(g);
	}

	@Override
	public Pice delete(Long id) {
		// TODO Auto-generated method stub
		Pice Pice = PiceRep.findOne(id);
		if (Pice == null) {
			try {
				throw new Exception("Pice nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PiceRep.delete(Pice);
		return Pice;
	}
}
