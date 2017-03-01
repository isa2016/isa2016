package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Rejon;
import restoran.repozitorijum.RejonRep;
import restoran.servis.RejonServis;

@Service
@Transactional
public class RejonServisImpl implements RejonServis{

	@Autowired
	private RejonRep RejonRep;
	
	@Override
	public List<Rejon> findAll() {
		return RejonRep.findAll();
	}

	@Override
	public Rejon findOne(Long id) {
		// TODO Auto-generated method stub
		return RejonRep.findOne(id);
	}

	@Override
	public Rejon save(Rejon g) {
		// TODO Auto-generated method stub
		return RejonRep.save(g);
	}

	@Override
	public Rejon delete(Long id) {
		// TODO Auto-generated method stub
		Rejon Rejon = RejonRep.findOne(id);
		if (Rejon == null) {
			try {
				throw new Exception("Rejon nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RejonRep.delete(Rejon);
		return Rejon;
	}


}
