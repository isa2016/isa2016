package restoran.kontroleri;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Porudzbina;
import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;
import restoran.servis.RezervacijaServis;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

	
	private final RezervacijaServis rezervacijaServis;
	private HttpSession httpSession;

	@Autowired
	public RezervacijaController(final HttpSession httpSession, final RezervacijaServis servis) {
		this.rezervacijaServis = servis;
		this.httpSession = httpSession;
	}
	
	@GetMapping("/{id}")
	public Porudzbina findOne(@PathVariable Long id) {
		
		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		
		Rezervacija r = rezervacijaServis.findOne(id);
		Porudzbina p1 = null;
		
		for(Porudzbina p : r.getPorudzbine()){
			if(p.getGost().getId().equals(gostID)){
				p1=p;
			}
		}
		
		return p1;
	}
}
