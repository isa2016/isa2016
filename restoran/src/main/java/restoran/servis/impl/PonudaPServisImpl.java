package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.PonudaP;
import restoran.model.Porudzbina;
import restoran.repozitorijum.PonudaPRep;
import restoran.servis.PonudaPServis;

@Service
@Transactional
public class PonudaPServisImpl implements PonudaPServis{

	@Autowired
	private PonudaPRep ponudaPRep;
	
	@Override
	public List<PonudaP> findAll() {
		// TODO Auto-generated method stub
		return ponudaPRep.findAll();
	}

	@Override
	public PonudaP findOne(Long id) {
		// TODO Auto-generated method stub
		return ponudaPRep.findById(id);
	}

	@Override
	public PonudaP save(PonudaP g) {
		// TODO Auto-generated method stub
		return ponudaPRep.save(g);
	}

	@Override
	public PonudaP delete(Long id) {
		// TODO Auto-generated method stub
		PonudaP PonudaP = ponudaPRep.findOne(id);
		if (PonudaP == null) {
			try {
				throw new Exception("Ponuda nije pronadjena");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ponudaPRep.delete(PonudaP);
		return PonudaP;
	}

}
