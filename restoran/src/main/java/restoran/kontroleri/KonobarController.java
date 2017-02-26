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
import restoran.model.Porudzbina;
import restoran.model.osoba.Konobar;
import restoran.servis.KonobarServis;
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

	@Autowired
	public KonobarController(final RezervacijaServis rezS, final KonobarServis ks, HttpSession session,
			final RestoranServis rs, final PorudzbinaServis ps) {
		this.ks = ks;
		this.session = session;
		this.rs = rs;
		this.rezS = rezS;
		this.ps = ps;
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
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}

	@PostMapping(path = "unesi/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void unesiPorudzbinu(@PathVariable Long id) {

		Porudzbina p = ps.findOne(id);
		if (!p.getHranaStatus().equals(HranaStatus.FINISHED))
			p.setHranaStatus(HranaStatus.ONHOLD);
		if (!p.getPiceStatus().equals(PiceStatus.FINISHED))
			p.setPiceStatus(PiceStatus.ONHOLD);
		ps.save(p);
	}

}
