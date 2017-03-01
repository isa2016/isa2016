package restoran.servis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restoran.model.ObjavaPonude;
import restoran.repozitorijum.ObjavaRep;
import restoran.servis.ObjavaPonudeServis;


@Service
@Transactional
public class ObjavaServisImpl implements ObjavaPonudeServis{


	@Autowired
	private ObjavaRep ObjavaRep;

	
	public List<ObjavaPonude> findAll() {
		// TODO Auto-generated method stub
		return ObjavaRep.findAll();
	}

	
	public ObjavaPonude findOne(Long id) {
		// TODO Auto-generated method stub
		return ObjavaRep.findById(id);
	}

	public ObjavaPonude save(ObjavaPonude g) {
		// TODO Auto-generated method stub
		return ObjavaRep.save(g);
	}

	public ObjavaPonude delete(Long id) {
		// TODO Auto-generated method stub
		ObjavaPonude ObjavaPonude = ObjavaRep.findOne(id);
		if (ObjavaPonude == null) {
			try {
				throw new Exception("Objava ponude nije pronadjena");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ObjavaRep.delete(ObjavaPonude);
		return ObjavaPonude;
	}

}
