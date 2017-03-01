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
import restoran.model.osoba.Kuvar;
import restoran.servis.KuvarServis;
import restoran.servis.PorudzbinaServis;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/kuvar")
public class KuvarController {
	private final KuvarServis ks;
	private HttpSession session;
	private PorudzbinaServis ps;
	private RestoranServis rs;

	@Autowired
	public KuvarController(final RestoranServis rs, final PorudzbinaServis ps, final KuvarServis ks,
			HttpSession session) {
		this.ks = ks;
		this.rs = rs;
		this.ps = ps;
		this.session = session;
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Kuvar update(@PathVariable Long id, @RequestBody Kuvar k) {
		Optional.ofNullable(ks.findOne(id)).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		k.setId(id);
		session.setAttribute("korisnik", k);
		return ks.save(k);
	}

	@GetMapping("/porudzbine")
	public ResponseEntity<List<Porudzbina>> findAllPorudzbine() {
		Kuvar k = ((Kuvar) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (int i = 0; i < ps.findAll().size(); i++) {
			Porudzbina por = ps.findAll().get(i);
			if (por.getRestoranId().equals(k.getRestoranId()) && por.getHranaStatus().equals(HranaStatus.ONHOLD)) {
				porudzbine.add(por);
			}
		}
		for (int j = porudzbine.size() - 1; j >= 0; j--) {
			if (porudzbine.get(j).getHrana().size() == 0) {
				porudzbine.get(j).setHranaStatus(HranaStatus.FINISHED);
				porudzbine.remove(j);
			}
		}
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}

	@GetMapping("/porudzbinePriprema")
	public ResponseEntity<List<Porudzbina>> findAllPorudzbinePriprema() {
		Kuvar k = ((Kuvar) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for (int i = 0; i < ps.findAll().size(); i++) {
			Porudzbina por = ps.findAll().get(i);
			if (por.getRestoranId().equals(k.getRestoranId()) && por.getHranaStatus().equals(HranaStatus.PREPARATION)) {
				porudzbine.add(por);
			}
		}

		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}

	@PostMapping(path = "prihvati/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void prihvatiPorudzbinu(@PathVariable Long id) {

		Porudzbina p = ps.findOne(id);
		p.setHranaStatus(HranaStatus.PREPARATION);
		ps.save(p);
	}

	@PostMapping(path = "zavrsi/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void zavrsiPorudzbinu(@PathVariable Long id) {

		Porudzbina p = ps.findOne(id);
		p.setHranaStatus(HranaStatus.FINISHED);
		ps.save(p);
	}

	/*
	 * @GetMapping("/porudzbine") public ResponseEntity<List<Porudzbina>>
	 * findAllPorudzbine() { Kuvar k = ((Kuvar)
	 * session.getAttribute("korisnik")); List<Porudzbina> porudzbine = new
	 * ArrayList<Porudzbina>(); for (int i = 0; i < ps.findAll().size(); i++) {
	 * Porudzbina por = ps.findAll().get(i); if
	 * (por.getRestoranId().equals(k.getRestoranId())) { porudzbine.add(por); }
	 * } for (int j = 0; j < porudzbine.size(); j++) for (int n =
	 * porudzbine.get(j).getHrana().size() - 1; n >= 0; n--) { if
	 * (!(porudzbine.get(j).getHrana().get(n).getTipJela().toString().equals(k.
	 * getTipKuvara().toString())) ||
	 * !(porudzbine.get(j).getHrana().get(n).getStatusJela().equals(StatusJela.
	 * ONHOLD))) porudzbine.get(j).getHrana().remove(n); } for (int n =
	 * porudzbine.size() - 1; n >= 0; n--) { if
	 * (porudzbine.get(n).getHrana().size() == 0) porudzbine.remove(n); } return
	 * new ResponseEntity<>(porudzbine, HttpStatus.OK); }
	 * 
	 * @GetMapping("/porudzbinePriprema") public
	 * ResponseEntity<List<Porudzbina>> findAllPorudzbinePriprema() { Kuvar k =
	 * ((Kuvar) session.getAttribute("korisnik")); List<Porudzbina> porudzbine =
	 * new ArrayList<Porudzbina>(); for (int i = 0; i < ps.findAll().size();
	 * i++) { Porudzbina por = ps.findAll().get(i); if
	 * (por.getRestoranId().equals(k.getRestoranId())) { porudzbine.add(por); }
	 * } for (int i = 0; i < porudzbine.size(); i++) for (int j =
	 * porudzbine.get(i).getHrana().size() - 1; j >= 0; j--) if
	 * (!(porudzbine.get(i).getHrana().get(j).getTipJela().toString().equals(k.
	 * getTipKuvara().toString())) ||
	 * !(porudzbine.get(i).getHrana().get(j).getStatusJela().equals(StatusJela.
	 * PREPARATION))) porudzbine.get(i).getHrana().remove(j); for (int n =
	 * porudzbine.size() - 1; n >= 0; n--) { if
	 * (porudzbine.get(n).getHrana().size() == 0) porudzbine.remove(n); } return
	 * new ResponseEntity<>(porudzbine, HttpStatus.OK); }
	 * 
	 * @PostMapping(path = "prihvati/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public void
	 * prihvatiPorudzbinu(@PathVariable Long id) { Kuvar k = ((Kuvar)
	 * session.getAttribute("korisnik")); Porudzbina p = ps.findOne(id); for
	 * (Jelo j : p.getHrana()) { if
	 * (j.getTipJela().toString().equals(k.getTipKuvara().toString()))
	 * j.setStatusJela(StatusJela.PREPARATION); } //
	 * p.setHranaStatus(HranaStatus.PREPARATION); ps.save(p); }
	 */
}
