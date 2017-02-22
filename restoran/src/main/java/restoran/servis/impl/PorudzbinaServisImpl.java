package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Porudzbina;
import restoran.repozitorijum.PorudzbinaRep;
import restoran.servis.PorudzbinaServis;

@Service
@Transactional
public class PorudzbinaServisImpl implements PorudzbinaServis{

	@Autowired
	private PorudzbinaRep pRep;

	@Override
	public List<Porudzbina> findAll() {
		return pRep.findAll();
	}

	@Override
	public Porudzbina findOne(Long id) {
		// TODO Auto-generated method stub
		return pRep.findOne(id);
	}

	@Override
	public Porudzbina save(Porudzbina g) {
		// TODO Auto-generated method stub
		return pRep.save(g);
	}

	@Override
	public Porudzbina delete(Long id) {
		// TODO Auto-generated method stub
		Porudzbina Porudzbina = pRep.findOne(id);
		if (Porudzbina == null) {
			try {
				throw new Exception("Jelo nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pRep.delete(Porudzbina);
		return Porudzbina;
	}

}
