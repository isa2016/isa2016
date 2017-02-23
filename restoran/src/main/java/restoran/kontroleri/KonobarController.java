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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Porudzbina;
import restoran.model.Restoran;
import restoran.model.Rezervacija;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.servis.KonobarServis;
import restoran.servis.RestoranServis;
import restoran.servis.RezervacijaServis;

@RestController
@RequestMapping("/konobar")
public class KonobarController {

	private final KonobarServis ks;
	private HttpSession session;
	private final RestoranServis rs;
	private final RezervacijaServis rezS;
	
	@Autowired
	public KonobarController(final RezervacijaServis rezS, final KonobarServis ks, HttpSession session, final RestoranServis rs){
		this.ks = ks;
		this.session = session;
		this.rs = rs;
		this.rezS = rezS;
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Konobar update(@PathVariable Long id, @RequestBody Konobar k) {
		Optional.ofNullable(ks.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		k.setId(id);
		session.setAttribute("korisnik", k);
		return ks.save(k);
	}
	
	@GetMapping("/porudzbine")
	public ResponseEntity<List<Porudzbina>> findAllPorudzbine() {
		Long id = ((Konobar) session.getAttribute("korisnik")).getId();
		List<Restoran> l = rs.findAll();
		Restoran rest = new Restoran();
		for (int i = 0; i < l.size(); i++) {
			Restoran r = l.get(i);
			for (int j = 0; j < r.getKuvari().size(); j++) {
				if (id.equals(r.getKuvari().get(j).getId())) {
					rest = r;
				}
			}
		}
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		List<Rezervacija> rezervacije = rezS.findAll();
		for (int i = 0; i < rezervacije.size(); i++)
			if (rezervacije.get(i).getRestaurant().getId().equals(rest.getId()))
				porudzbine.addAll(rezervacije.get(i).getPorudzbine());
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}
}
