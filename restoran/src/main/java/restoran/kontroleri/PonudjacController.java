package restoran.kontroleri;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.ObjavaPonude;
import restoran.model.PonudaP;
import restoran.model.Restoran;
import restoran.model.osoba.MenadzerRestorana;
import restoran.model.osoba.Ponudjac;
import restoran.servis.MenadzerRestoranaServis;
import restoran.servis.ObjavaPonudeServis;
import restoran.servis.PonudaPServis;
import restoran.servis.PonudjacServis;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/ponudjac")
public class PonudjacController {

	private final PonudjacServis ponudjacServis;
	private HttpSession httpSession;

	private RestoranServis restoranServis;
	private final MenadzerRestoranaServis mrServis;
	private final PonudaPServis ponudaPServis;
	private final ObjavaPonudeServis objavaPonudeServis;

	@Autowired
	public PonudjacController(final HttpSession httpSession, final PonudjacServis servis,
			final RestoranServis restServis, final MenadzerRestoranaServis mRServis, final PonudaPServis pServis,
			final ObjavaPonudeServis ops) {
		this.ponudjacServis = servis;
		this.httpSession = httpSession;
		this.restoranServis = restServis;
		this.mrServis = mRServis;
		this.ponudaPServis = pServis;
		this.objavaPonudeServis = ops;
	}
	// private RestoranServis restoranServis;

	@PutMapping(path = "/ponudjacUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Ponudjac update(@PathVariable Long id, @Valid @RequestBody Ponudjac ponudjac) {

		Optional.ofNullable(ponudjacServis.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		ponudjac.setId(id);
		httpSession.setAttribute("korisnik", ponudjac);
		return ponudjacServis.save(ponudjac);

	}

	@GetMapping(path = "/detaljiPonuda/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ObjavaPonude ponuda(@PathVariable Long id) {

		ObjavaPonude op = objavaPonudeServis.findOne(id);
		return op;
	}

	@GetMapping("/ponudjacObjave/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ObjavaPonude>> sveObjave(@PathVariable Long id)  {
		System.out.println("Ponudjaciiiiiiiiiiiiiii");
		Ponudjac p = ponudjacServis.findOne(id);
		
		
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();

		
				
		
		
		
        for(int i = 0; i<p.getObjave().size(); i++){
        	try {
				Date date = format.parse(p.getObjave().get(i).getKrajaVazenja());
				if(!date.after(today)){
					p.getObjave().remove(i);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ponudjacServis.save(p);
        List<ObjavaPonude> op = p.getObjave();
		return new ResponseEntity<>(op, HttpStatus.OK);
	}

	@GetMapping("/kreirajPonudu/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Restoran> kreirajObjave(@PathVariable Long id) {

		Restoran res = restoranServis.findOne(id);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping(path = "/ponudjacPonuda/{id}/{idd}/{id2}/{cena}/{garancija}/{isporuka}")
	@ResponseStatus(HttpStatus.OK)
	public void posaljiPonudu(@PathVariable Long id, @PathVariable Long idd, @PathVariable Long id2,
			@PathVariable String cena, @PathVariable String garancija, @PathVariable String isporuka) {

		Ponudjac p = ponudjacServis.findOne(id2);
		double ce = Integer.parseInt(cena);
		int gara = Integer.parseInt(garancija);
		/*
		 * for(int j=0; j<p.getObjave().size();j++){
		 * if(idd.equals(p.getObjave().get(j).getId())){
		 * p.getObjave().remove(j); } }
		 */
		Restoran r = restoranServis.findOne(id);

		PonudaP pp = new PonudaP();

		pp.setPonudjac_Id(id2);
		pp.setCena(ce);
		pp.setGarancija(gara);
		pp.setRokIsporuke(isporuka);
		pp.setObjava_ponude_Id(idd);
		ponudaPServis.save(pp);
		p.getPonuda().add(pp);
		List<MenadzerRestorana> mr = r.getMenadzeriRestorana();

		MenadzerRestorana m = null;

		for (int i = 0; i < mr.size(); i++) {
			if (id.equals(mr.get(i).getRestoranId())) {
				m = mr.get(i);
			}
		}

		Long idi = new Long(ponudaPServis.findAll().size());

		m.getPonudaP().add(ponudaPServis.findOne(idi));

		mrServis.save(m);
		ponudjacServis.save(p);
	}

	@PutMapping(path = "/menjanjePonuda/{id}/{cena}/{garancija}/{isporuka}")
	@ResponseStatus(HttpStatus.OK)
	public void mijenjajPonudu(@PathVariable Long id, @PathVariable String cena, @PathVariable String garancija,
			@PathVariable String isporuka) {

		// Ponudjac p = ponudjacServis.findOne(id2);
		double ce = Integer.parseInt(garancija);
		int gara = Integer.parseInt(cena);

		ponudaPServis.findOne(id).setCena(ce);
		ponudaPServis.findOne(id).setGarancija(gara);
		ponudaPServis.findOne(id).setRokIsporuke(isporuka);
		ponudaPServis.save(ponudaPServis.findOne(id));
		/*
		 * p.getPonuda().add(pp); List<MenadzerRestorana> mr =
		 * r.getMenadzeriRestorana();
		 * 
		 * MenadzerRestorana m = null;
		 * 
		 * for(int i=0;i<mr.size();i++){
		 * if(id.equals(mr.get(i).getRestoranId())){ m = mr.get(i); } }
		 * 
		 * Long idi = new Long(ponudaPServis.findAll().size());
		 * 
		 * m.getPonudaP().add(ponudaPServis.findOne(idi));
		 * 
		 * mrServis.save(m); ponudjacServis.save(p);
		 */
	}

	@GetMapping("/ponudjacPonude/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<PonudaP>> svePonude(@PathVariable Long id) {

		Ponudjac p = ponudjacServis.findOne(id);
		List<PonudaP> op = p.getPonuda();

		return new ResponseEntity<>(op, HttpStatus.OK);
	}
}
