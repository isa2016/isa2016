package restoran.kontroleri;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

	
	//private RezervacijaServis rezServis;
	
	@Autowired
	public RezervacijaController( ) {
		//this.rezServis = rezServis;
		//this.httpSession = httpSession;

	}
	
	
	@PostMapping(path = "/dodaj")
	//@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Gost gost,@RequestBody Rezervacija rez) {

		System.out.println(rez.getDuration());
		System.out.println(rez.getHours());
		System.out.println(rez.getMinutes());
		//rezServis.save(rez);
	}
}
