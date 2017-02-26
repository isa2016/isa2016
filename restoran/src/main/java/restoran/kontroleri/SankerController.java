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

import restoran.enumeracije.PiceStatus;
import restoran.model.Porudzbina;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.Sanker;
import restoran.servis.PorudzbinaServis;
import restoran.servis.SankerServis;

@RestController
@RequestMapping("/sanker")
public class SankerController {
	private final SankerServis ss;
	private HttpSession session;
	private final PorudzbinaServis ps;
	
	@Autowired
	public SankerController(final PorudzbinaServis ps, final SankerServis ss, HttpSession session){
		this.ss = ss;
		this.session = session;
		this.ps = ps;
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Sanker update(@PathVariable Long id, @RequestBody Sanker s) {
		Optional.ofNullable(ss.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		s.setId(id);
		session.setAttribute("korisnik", s);
		return ss.save(s);
	}

	@GetMapping("/porudzbine")
	public ResponseEntity<List<Porudzbina>> findAllPorudzbine() {
		Sanker s = ((Sanker) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for(int i=0; i<ps.findAll().size();i++){
			Porudzbina por = ps.findAll().get(i);
			if(por.getRestoranId().equals(s.getRestoranId()) && por.getPiceStatus().equals(PiceStatus.ONHOLD)){
				porudzbine.add(por);
			}
		}
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}
}
