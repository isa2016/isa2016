package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.Sto;
import restoran.repozitorijum.StoRep;
import restoran.servis.StoServis;


@Service
@Transactional
public class StoServisImpl implements StoServis{

	@Autowired
	private StoRep StoRep;

	@Override
	public List<Sto> findAll() {
		return StoRep.findAll();
	}

	@Override
	public Sto findOne(Long id) {
		// TODO Auto-generated method stub
		return StoRep.findOne(id);
	}

	@Override
	public Sto save(Sto g) {
		// TODO Auto-generated method stub
		return StoRep.save(g);
	}

	@Override
	public Sto delete(Long id) {
		// TODO Auto-generated method stub
		Sto Sto = StoRep.findOne(id);
		if (Sto == null) {
			try {
				throw new Exception("Sto nije pronadjen");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		StoRep.delete(Sto);
		return Sto;
	}
}
