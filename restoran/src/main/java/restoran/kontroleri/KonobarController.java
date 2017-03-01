package restoran.kontroleri;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
import restoran.enumeracije.PorudzbinaStatus;
import restoran.enumeracije.StatusJela;
import restoran.model.Jelo;
import restoran.model.Pice;
import restoran.model.Porudzbina;
import restoran.model.Restoran;
import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;
import restoran.model.osoba.Konobar;
import restoran.servis.JeloServis;
import restoran.servis.KonobarServis;
import restoran.servis.PiceServis;
import restoran.servis.PorudzbinaServis;
import restoran.servis.RestoranServis;
import restoran.servis.RezervacijaServis;

@RestController
@RequestMapping("/konobar")
public class KonobarController {

	private final KonobarServis ks;
	private HttpSession session;
	private final RestoranServis rs;
	private final RezervacijaServis rezS;
	private final PorudzbinaServis ps;
	private final PiceServis piceServis;
	private final JeloServis jeloServis;

	@Autowired
	public KonobarController(final PiceServis piceServis, final JeloServis jeloServis, final RezervacijaServis rezS,
			final KonobarServis ks, HttpSession session, final RestoranServis rs, final PorudzbinaServis ps) {
		this.ks = ks;
		this.session = session;
		this.rs = rs;
		this.rezS = rezS;
		this.ps = ps;
		this.piceServis = piceServis;
		this.jeloServis = jeloServis;
	}

	@GetMapping(path = "/{id}")
	public Restoran list(@PathVariable Long id) {
		Konobar k = ks.findOne(id);
		return rs.findOne(k.getRestoranId());
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Konobar update(@PathVariable Long id, @RequestBody Konobar k) {
		Optional.ofNullable(ks.findOne(id)).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		k.setId(id);
		session.setAttribute("korisnik", k);
		return ks.save(k);
	}

	@GetMapping("/porudzbine")
	public ResponseEntity<List<Porudzbina>> findPorudzbine() {
		Konobar k = ((Konobar) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (int i = 0; i < ps.findAll().size(); i++) {
			Porudzbina por = ps.findAll().get(i);
			if (por.getRestoranId().equals(k.getRestoranId()) && (por.getHranaStatus().equals(HranaStatus.ORDERED)
					|| por.getPiceStatus().equals(PiceStatus.ORDERED))) {
				porudzbine.add(por);
			}
		}
		for (int j = porudzbine.size() - 1; j >= 0; j--) {
			if (porudzbine.get(j).getHrana().size() == 0) {
				porudzbine.get(j).setHranaStatus(HranaStatus.FINISHED);
			}
			if (porudzbine.get(j).getPice().size() == 0) {
				porudzbine.get(j).setPiceStatus(PiceStatus.FINISHED);
			}
			if (porudzbine.get(j).getPiceStatus().equals(PiceStatus.FINISHED)
					&& porudzbine.get(j).getHranaStatus().equals(HranaStatus.FINISHED))
				porudzbine.remove(j);
		}
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}

	@GetMapping("/porudzbineNaCekanju")
	public ResponseEntity<List<Porudzbina>> findHoldOnPorudzbine() {
		Konobar k = ((Konobar) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (int i = 0; i < ps.findAll().size(); i++) {
			Porudzbina por = ps.findAll().get(i);
			if (por.getRestoranId().equals(k.getRestoranId()) && (por.getHranaStatus().equals(HranaStatus.ONHOLD)
					|| por.getHranaStatus().equals(HranaStatus.PREPARATION)
					|| por.getPiceStatus().equals(PiceStatus.ONHOLD))) {
				porudzbine.add(por);
			}
		}
		for (int j = porudzbine.size() - 1; j >= 0; j--) {
			if (porudzbine.get(j).getHrana().size() == 0) {
				porudzbine.get(j).setHranaStatus(HranaStatus.FINISHED);
			}
			if (porudzbine.get(j).getPice().size() == 0) {
				porudzbine.get(j).setPiceStatus(PiceStatus.FINISHED);
			}
			if (porudzbine.get(j).getPiceStatus().equals(PiceStatus.FINISHED)
					&& porudzbine.get(j).getHranaStatus().equals(HranaStatus.FINISHED))
				porudzbine.remove(j);
		}
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}

	@GetMapping("/porudzbineGotove")
	public ResponseEntity<List<Porudzbina>> findFinishedPorudzbine() {
		Konobar k = ((Konobar) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (int i = 0; i < ps.findAll().size(); i++) {
			Porudzbina por = ps.findAll().get(i);
			if (por.getRestoranId().equals(k.getRestoranId())
					&& (por.getHranaStatus().equals(HranaStatus.FINISHED)
							&& por.getPiceStatus().equals(PiceStatus.FINISHED))
					&& por.getPorudzbinaStatus().equals(PorudzbinaStatus.UNPAID)) {
				porudzbine.add(por);
			}
		}

		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}

	@PostMapping(path = "unesi/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void unesiPorudzbinu(@PathVariable Long id) {

		Porudzbina p = ps.findOne(id);
		if (!p.getHranaStatus().equals(HranaStatus.FINISHED)) {
			p.setHranaStatus(HranaStatus.ONHOLD);
			// for(Jelo j : p.getHrana())
			// j.setStatusJela(StatusJela.ONHOLD);
		}

		if (!p.getPiceStatus().equals(PiceStatus.FINISHED))
			p.setPiceStatus(PiceStatus.ONHOLD);
		ps.save(p);
	}

	@PostMapping(path = "potvrdi/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void potvrdiRacun(@PathVariable Long id) {

		Porudzbina p = ps.findOne(id);
		p.setPorudzbinaStatus(PorudzbinaStatus.PAID);
		ps.save(p);
	}

	@GetMapping("/racun/{id}")
	public Porudzbina zavrsiRacun(@PathVariable Long id) {
		Porudzbina p = ps.findOne(id);
		// p.setPorudzbinaStatus(PorudzbinaStatus.PAID);
		return p;
	}

	@GetMapping("/izmeni/{id}")
	public Porudzbina izmeniPorudzbinu(@PathVariable Long id) {
		Porudzbina p = ps.findOne(id);
		// p.setPorudzbinaStatus(PorudzbinaStatus.PAID);
		System.out.println("djesuuu?");
		return p;
	}
	
	@GetMapping("/porudzbineJelo/{id}/{id2}")
	public ResponseEntity<Porudzbina> dodajJ(@PathVariable Long id, @PathVariable Long id2) {
		Jelo j = jeloServis.findOne(id);
		Porudzbina p = ps.findOne(id2);
		p.getHrana().add(j);
		ps.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/porudzbinePice/{id}/{id2}")
	public ResponseEntity<Porudzbina> dodajP(@PathVariable Long id, @PathVariable Long id2) {
		Pice pice = piceServis.findOne(id);
		Porudzbina p = ps.findOne(id2);
		p.getPice().add(pice);
		ps.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/izbrisiJelo/{id}/{id2}")
	public ResponseEntity<Porudzbina> izbrisiJelo(@PathVariable Long id, @PathVariable Long id2) {
		Jelo jelo = jeloServis.findOne(id);
		Porudzbina p = ps.findOne(id2);
		p.getHrana().remove(jelo);
		if (p.getHrana().size() == 0 && p.getPice().size() == 0)
			ps.delete(id2);
		else
			ps.save(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/izbrisiPice/{id}/{id2}")
	public ResponseEntity<Porudzbina> izbrisiPice(@PathVariable Long id, @PathVariable Long id2) {
		Pice pice = piceServis.findOne(id);
		Porudzbina p = ps.findOne(id2);
		p.getPice().remove(pice);
		if (p.getHrana().size() == 0 && p.getPice().size() == 0)
			ps.delete(id2);
		else
			ps.save(p);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/napraviPJ/{id}")
	public ResponseEntity<Porudzbina> napraviPJ(@PathVariable Long id) {
		Jelo j = jeloServis.findOne(id);
		Porudzbina p = new Porudzbina();
		p.setPorudzbinaStatus(PorudzbinaStatus.UNPAID);
		p.getHrana().add(j);
		ps.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@GetMapping("/napraviPP/{id}")
	public ResponseEntity<Porudzbina> napraviPP(@PathVariable Long id) {
		Pice j = piceServis.findOne(id);
		Porudzbina p = new Porudzbina();
		p.setPorudzbinaStatus(PorudzbinaStatus.UNPAID);

		p.getPice().add(j);
		ps.save(p);

		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@PostMapping(path = "/naruci/{id}/{id2}")
	@ResponseStatus(HttpStatus.CREATED)
	public void poruci(@PathVariable Long id, @PathVariable Long id2) {
		Porudzbina p = ps.findOne(id2);
		p.setRestoranId(id);
		if (p.getHrana().size() == 0)
			p.setHranaStatus(HranaStatus.FINISHED);
		else {
			p.setHranaStatus(HranaStatus.ORDERED);
			for (Jelo j : p.getHrana())
				j.setStatusJela(StatusJela.ORDERED);
		}
		if (p.getPice().size() == 0)
			p.setPiceStatus(PiceStatus.FINISHED);
		else
			p.setPiceStatus(PiceStatus.ORDERED);
		ps.save(p);
	}

}
