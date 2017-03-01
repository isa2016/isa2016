package restoran.kontroleri;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.enumeracije.PorudzbinaStatus;
import restoran.model.Jelo;
import restoran.model.Pice;
import restoran.model.PonudaP;
import restoran.model.Porudzbina;
import restoran.model.Restoran;
import restoran.model.Sto;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.MenadzerRestorana;
import restoran.model.osoba.Ponudjac;
import restoran.model.osoba.Sanker;
import restoran.servis.MenadzerRestoranaServis;
import restoran.servis.PonudaPServis;
import restoran.servis.PonudjacServis;
import restoran.servis.PorudzbinaServis;
import restoran.servis.RestoranServis;
import restoran.servis.StoServis;

@RestController
@RequestMapping("/menadzer")
public class MenadzerController {

	private final MenadzerRestoranaServis menadzerRestServis;
	private RestoranServis restoranServis;
	private final PonudaPServis ponudaPServis;
	private final PonudjacServis ponudjacServis;

    private final StoServis stoServis;
	private final PorudzbinaServis porudzbinaServis;

	@Autowired
	public MenadzerController(final RestoranServis restServis, final MenadzerRestoranaServis servis,
			final PonudaPServis pservis,final StoServis stoservis, final PonudjacServis ponServis, final PorudzbinaServis porudzbinaServis) {

		this.menadzerRestServis = servis;
		this.restoranServis = restServis;
		this.ponudaPServis = pservis;
		this.porudzbinaServis = porudzbinaServis;
		this.ponudjacServis = ponServis;
		this.stoServis = stoservis;
	}

	@PutMapping(path = "/updateRest/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restoran update(@PathVariable Long id, @Valid @RequestBody Restoran rest) {
		Optional.ofNullable(restoranServis.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		rest.setId(id);
		return restoranServis.save(rest);
	}

	@GetMapping(path = "/{id}")
	public Restoran list(@PathVariable Long id) {
		List<Restoran> l = restoranServis.findAll();
		for (int i = 0; i < l.size(); i++) {
			Restoran r = l.get(i);
			for (int j = 0; j < r.getMenadzeriRestorana().size(); j++) {
				if (id.equals(r.getMenadzeriRestorana().get(j).getId())) {
					return r;
				}
			}
		}
		return null;
	}

	@GetMapping(path = "nadji/{id}/{ime}")
	public Jelo jelo(@PathVariable Long id, @PathVariable String ime) {
		Jelo p = null;
		Restoran r = restoranServis.findOne(id);

		List<Jelo> j = r.getJelovnik();
		for (int i = 0; i < j.size(); i++) {
			if (ime.equals(j.get(i).getNaziv())) {
				p = j.get(i);
			}
		}
		return p;
	}

	@GetMapping(path = "pronadji/{id}/{ime}")
	public Pice pice(@PathVariable Long id, @PathVariable String ime) {
		Pice pi = null;
		Restoran r = restoranServis.findOne(id);

		List<Pice> pice = r.getKartaPica();
		for (int i = 0; i < pice.size(); i++) {
			if (ime.equals(pice.get(i).getNaziv())) {
				pi = pice.get(i);
			}
		}
		return pi;
	}

	@PostMapping(path = "jelo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajJelo(@PathVariable Long id, @Valid @RequestBody Jelo jelo) {

		Restoran restaurant = restoranServis.findOne(id);

		restaurant.getJelovnik().add(jelo);
		restoranServis.save(restaurant);
	}

	@PostMapping(path = "pice/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajPice(@PathVariable Long id, @Valid @RequestBody Pice pice) {
		Restoran restaurant = restoranServis.findOne(id);
		restaurant.getKartaPica().add(pice);
		restoranServis.save(restaurant);
	}

	@PostMapping(path = "rejon/{rejon}/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajPice(@PathVariable String rejon,@PathVariable Long id) {
		
		System.out.println(rejon);
		Long idi = Long.parseLong(rejon);
		System.out.println(idi);
		Sto s = new Sto();
		s.setRejon(idi);
		
		Restoran r = restoranServis.findOne(id);
		r.getStolovi().add(s);
		stoServis.save(s);
		restoranServis.save(r);
	}

	
	@PostMapping(path = "kuvar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajKuvara(@PathVariable Long id, @Valid @RequestBody Kuvar kuvar) {
		Restoran restaurant = restoranServis.findOne(id);
		kuvar.setRegistrovan("0");
		kuvar.setRestoranId(id);
		restaurant.getKuvari().add(kuvar);
		restoranServis.save(restaurant);
	}

	@PostMapping(path = "konobar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajKonobara(@PathVariable Long id, @Valid @RequestBody Konobar konobar) {
		Restoran restaurant = restoranServis.findOne(id);
		konobar.setRegistrovan("0");
		konobar.setRestoranId(id);
		restaurant.getKonobari().add(konobar);
		restoranServis.save(restaurant);
	}

	@PostMapping(path = "sanker/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajSankera(@PathVariable Long id, @Valid @RequestBody Sanker sanker) {
		Restoran restaurant = restoranServis.findOne(id);
		sanker.setRegistrovan("0");
		sanker.setRestoranId(id);
		restaurant.getSankeri().add(sanker);
		restoranServis.save(restaurant);
	}

	@PostMapping(path = "ponudjac/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void registrujPonudjaca(@PathVariable Long id, @Valid @RequestBody Ponudjac ponudjac) {
		Restoran restaurant = restoranServis.findOne(id);
		restaurant.getPonudjaci().add(ponudjac);
		restoranServis.save(restaurant);
	}

	@GetMapping("/menadzerPonude/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<PonudaP>> sveObjave(@PathVariable Long id) {

		MenadzerRestorana mr = menadzerRestServis.findOne(id);
		List<PonudaP> pp = mr.getPonudaP();

		return new ResponseEntity<>(pp, HttpStatus.OK);
	}

	@PostMapping(path = "prihvatiPonudu/{id}/{id2}/{id3}")
	@ResponseStatus(HttpStatus.CREATED)
	public void prihvatiPonudu(@PathVariable Long id, @PathVariable Long id2, @PathVariable Long id3) {
		MenadzerRestorana mr = menadzerRestServis.findOne(id2);
		PonudaP pp = ponudaPServis.findOne(id);
		Long ponudjacid = pp.getPonudjac_Id();
		Ponudjac p = ponudjacServis.findOne(ponudjacid);

		for (int i = 0; i < mr.getPonudaP().size(); i++) {
			if (id3.equals(mr.getPonudaP().get(i).getObjava_ponude_Id())) {
				if (pp.equals(mr.getPonudaP().get(i))) {
					mr.getPonudaP().get(i).setPrihvati("prihvaceno");
					mr.getPonudaP().remove(i);
				} else {
					mr.getPonudaP().get(i).setPrihvati("nije prihvaceno");
					mr.getPonudaP().remove(i);
				}
			}
		}

		for (int i = 0; i < p.getPonuda().size(); i++) {
			if (pp.getId().equals(p.getPonuda().get(i).getId())) {
				p.getPonuda().get(i).setPrihvati("prihvaceno");

			} else {
				p.getPonuda().get(i).setPrihvati("nije prihvaceno");

			}
		}
		menadzerRestServis.save(mr);
		ponudjacServis.save(p);

	}

	@GetMapping(path = "prihodi/{id}/{od}/{doo}")
	public double list(@PathVariable Long id, @PathVariable String od, @PathVariable String doo) {
		List<Porudzbina> porudzbine = porudzbinaServis.findAll();
		double total = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// int count = 0;
		Date odKad = new Date();
		Date doKad = new Date();
		try {
			odKad = format.parse(od);
			doKad = format.parse(doo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = porudzbine.size()-1; i >= 0; i--) {
			if (porudzbine.get(i).getRestoranId().equals(id)
					&& porudzbine.get(i).getPorudzbinaStatus().equals(PorudzbinaStatus.PAID)) {

				Date porudzbinaDatum = new Date();
				try {
					porudzbinaDatum = format.parse(porudzbine.get(i).getDatum());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (porudzbinaDatum.before(doKad) && porudzbinaDatum.after(odKad))
					total += porudzbine.get(i).getUkupnaCena();
			}
		}
		return total;

	}

}
