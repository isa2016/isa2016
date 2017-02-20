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

import restoran.model.Restoran;
import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;
import restoran.servis.GostServis;
import restoran.servis.RestoranServis;
import restoran.servis.RezervacijaServis;

@RestController
@RequestMapping("/guest")
public class GostController {

	private final GostServis gostServis;
	private final RestoranServis restoranServis;
	private final RezervacijaServis rezervacijaServis;
	
	private HttpSession httpSession;

	@Autowired
	public GostController(final HttpSession httpSession, final GostServis servis,final RestoranServis restoranServis,final RezervacijaServis rezervacijaServis) {
		this.gostServis = servis;
		this.httpSession = httpSession;
		this.restoranServis = restoranServis;
		this.rezervacijaServis = rezervacijaServis;

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
		for(Rezervacija r : sveRez){
			for(Gost g : r.getGosti()){
				if(g.getId().equals(gost.getId())){
					gostoveRez.add(r);
				}
			}
		}
		return new ResponseEntity<>(gostoveRez, HttpStatus.OK);
	}
	
	@PostMapping(path = "/rezervisi/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@PathVariable Long id,@RequestBody Rezervacija rez) {
		Restoran rest = restoranServis.findOne(id);
		rez.setRestaurant(rest);
		
		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		Gost gost = gostServis.findOne(gostID);
		
		rez.getGosti().add(gost);
		
		rezervacijaServis.save(rez);
		
	}

}