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
import restoran.model.osoba.Kuvar;
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

	@PostMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajKonobara(@Valid @RequestBody Kuvar cook, Long id) {

		Restoran restaurant = restoranServis.findOne(id);
		// restaurant.getKuvari().add(cook);
		restoranServis.save(restaurant);
	}

	
	@PostMapping(path = "jelo/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajJelo(@PathVariable Long id,@Valid @RequestBody Jelo jelo) {

		Restoran restaurant = restoranServis.findOne(id);
		restaurant.getJelovnik().add(jelo);
		restoranServis.save(restaurant);
	}
	
	@PostMapping(path = "pice/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajJelo(@PathVariable Long id,@Valid @RequestBody Pice pice) {
		Restoran restaurant = restoranServis.findOne(id);
		restaurant.getKartaPica().add(pice);
		restoranServis.save(restaurant);
	}
}
