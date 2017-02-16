package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Restoran;
import restoran.repozitorijum.RestoranRep;
import restoran.servis.RestoranServis;

@Service
@Transactional
public class RestoranServisImpl implements RestoranServis{

	@Autowired
	private RestoranRep RestoranRep;
	
	
	@Override
	public List<Restoran> findAll() {
		// TODO Auto-generated method stub
		return RestoranRep.findAll();
	}

	@Override
	public Restoran findOne(Long id) {
		// TODO Auto-generated method stub
		return RestoranRep.findOne(id);
	}

	@Override
	public Restoran save(Restoran g) {
		// TODO Auto-generated method stub
		return RestoranRep.save(g);
	}

	@Override
	public Restoran delete(Long id) {
		// TODO Auto-generated method stub
		Restoran Restoran = RestoranRep.findOne(id);
		if (Restoran == null) {
			try {
				throw new Exception("Restoran nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RestoranRep.delete(Restoran);
		return Restoran;
	}


}
