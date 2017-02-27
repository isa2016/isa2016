package restoran.kontroleri;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Jelo;
import restoran.model.Pice;
import restoran.model.Restoran;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.Ponudjac;
import restoran.model.osoba.Sanker;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/menadzer")
public class MenadzerController {

	// private final MenadzerRestoranaServis menadzerRestServis;
	// private HttpSession httpSession;
	private RestoranServis restoranServis;

	@Autowired
	public MenadzerController(final RestoranServis restServis) {
		// this.menadzerRestServis = servis;
		// this.httpSession = httpSession;
		this.restoranServis = restServis;
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

	// JE L' SE OVDE DODAJE KONOBAR ILI RESTORAN?
	/*
	 * @PostMapping(path = "/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public void
	 * dodajKuvar(@Valid @RequestBody Kuvar cook, Long id) {
	 * 
	 * Restoran restaurant = restoranServis.findOne(id); //
	 * restaurant.getKuvari().add(cook); restoranServis.save(restaurant); }
	 */

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

}
