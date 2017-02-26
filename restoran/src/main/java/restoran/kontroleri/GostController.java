package restoran.kontroleri;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.enumeracije.HranaStatus;
import restoran.enumeracije.PiceStatus;
import restoran.model.Jelo;
import restoran.model.Pice;
import restoran.model.Porudzbina;
import restoran.model.Restoran;
import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;
import restoran.servis.GostServis;
import restoran.servis.JeloServis;
import restoran.servis.PiceServis;
import restoran.servis.PorudzbinaServis;
import restoran.servis.RestoranServis;
import restoran.servis.RezervacijaServis;

@RestController
@RequestMapping("/guest")
public class GostController {

	private final GostServis gostServis;
	private final RestoranServis restoranServis;
	private final RezervacijaServis rezervacijaServis;
	private final JeloServis jeloServis;
	private final PorudzbinaServis porudzbinaServis;
	private final PiceServis piceServis;

	private HttpSession httpSession;

	@Autowired
	public GostController(final HttpSession httpSession, final JeloServis jeloServis, final GostServis servis,
			final RestoranServis restoranServis, final PiceServis piceServis, final PorudzbinaServis porudzbinaServis,
			final RezervacijaServis rezervacijaServis) {
		this.gostServis = servis;
		this.httpSession = httpSession;
		this.restoranServis = restoranServis;
		this.rezervacijaServis = rezervacijaServis;
		this.jeloServis = jeloServis;
		this.porudzbinaServis = porudzbinaServis;
		this.piceServis = piceServis;

	}

	@GetMapping
	public ResponseEntity<List<Gost>> findAll() {
		return new ResponseEntity<>(gostServis.findAll(), HttpStatus.OK);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Gost update(@PathVariable Long id, @Valid @RequestBody Gost guest) {
		Optional.ofNullable(gostServis.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		guest.setId(id);
		httpSession.setAttribute("korisnik", guest);
		return gostServis.save(guest);
	}

	@PutMapping(path = "/activate/{reg}")
	@ResponseStatus(HttpStatus.OK)
	public void activateGuest(@PathVariable String reg) {
		gostServis.aktiviraj(reg);
	}

	@GetMapping("/restorani")
	public ResponseEntity<List<Restoran>> findAllR() {
		return new ResponseEntity<>(restoranServis.findAll(), HttpStatus.OK);
	}

	@GetMapping("/rezervacije/{id}")
	public ResponseEntity<List<Rezervacija>> findAllRez(@PathVariable Long id) {

		Gost gost = gostServis.findOne(id);
		List<Rezervacija> sveRez = rezervacijaServis.findAll();
		List<Rezervacija> gostoveRez = new ArrayList<Rezervacija>();
		for (Rezervacija r : sveRez) {
			for (Gost g : r.getGosti()) {
				if (g.getId().equals(gost.getId())) {
					gostoveRez.add(r);
				}
			}
		}
		return new ResponseEntity<>(gostoveRez, HttpStatus.OK);
	}

	@PostMapping(path = "/rezervisi/{id}/{id2}")
	@ResponseStatus(HttpStatus.CREATED)
	public void potvrdaRezervacije(@PathVariable Long id, @PathVariable Long id2, @RequestBody Rezervacija rez) {
		Restoran rest = restoranServis.findOne(id);
		Porudzbina p = porudzbinaServis.findOne(id2);
		p.setRestoranId(id);
		if (p.getHrana().size() == 0)
			p.setHranaStatus(HranaStatus.FINISHED);
		else
			p.setHranaStatus(HranaStatus.ORDERED);
		if (p.getPice().size() == 0)
			p.setPiceStatus(PiceStatus.FINISHED);
		else
			p.setPiceStatus(PiceStatus.ORDERED);
		rez.setRestaurant(rest);
		rez.setPorudzbine(new ArrayList<Porudzbina>());
		rez.getPorudzbine().add(p);

		rez.getGosti().add(p.getGost());

		rezervacijaServis.save(rez);
	}

	@PostMapping(path = "/rezervisiBez/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void potvrdaRezervacije2(@PathVariable Long id, @RequestBody Rezervacija rez) {
		Restoran rest = restoranServis.findOne(id);

		rez.setRestaurant(rest);
		rez.setPorudzbine(new ArrayList<Porudzbina>());

		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		Gost gost = gostServis.findOne(gostID);
		rez.getGosti().add(gost);

		rezervacijaServis.save(rez);
	}

	@GetMapping("/porudzbineJelo/{id}/{id2}")
	public ResponseEntity<Porudzbina> dodajJ(@PathVariable Long id, @PathVariable Long id2) {
		Jelo j = jeloServis.findOne(id);
		Porudzbina p = porudzbinaServis.findOne(id2);
		p.getHrana().add(j);
		porudzbinaServis.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/porudzbinePice/{id}/{id2}")
	public ResponseEntity<Porudzbina> dodajP(@PathVariable Long id, @PathVariable Long id2) {
		Pice pice = piceServis.findOne(id);
		Porudzbina p = porudzbinaServis.findOne(id2);
		p.getPice().add(pice);
		porudzbinaServis.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/napraviPJ/{id}")
	public ResponseEntity<Porudzbina> napraviPJ(@PathVariable Long id) {
		Jelo j = jeloServis.findOne(id);
		Porudzbina p = new Porudzbina();

		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		Gost gost = gostServis.findOne(gostID);

		p.setGost(gost);
		p.getHrana().add(j);
		porudzbinaServis.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/napraviPP/{id}")
	public ResponseEntity<Porudzbina> napraviPP(@PathVariable Long id) {
		Pice j = piceServis.findOne(id);
		Porudzbina p = new Porudzbina();

		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		Gost gost = gostServis.findOne(gostID);

		p.setGost(gost);
		p.getPice().add(j);
		porudzbinaServis.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

}