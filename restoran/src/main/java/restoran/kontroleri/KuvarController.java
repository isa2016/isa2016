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

import restoran.enumeracije.HranaStatus;
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
	public KuvarController(final RestoranServis rs, final PorudzbinaServis ps, final KuvarServis ks, HttpSession session){
		this.ks = ks;
		this.rs = rs;
		this.ps = ps;
		this.session = session;
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Kuvar update(@PathVariable Long id, @RequestBody Kuvar k) {
		Optional.ofNullable(ks.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		k.setId(id);
		session.setAttribute("korisnik", k);
		return ks.save(k);
	}
	
	@GetMapping("/porudzbine")
	public ResponseEntity<List<Porudzbina>> findAllPorudzbine() {
		Kuvar k = ((Kuvar) session.getAttribute("korisnik"));
		List<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
		for(int i=0; i<ps.findAll().size();i++){
			Porudzbina por = ps.findAll().get(i);
			if(por.getRestoranId().equals(k.getRestoranId()) && por.getHranaStatus().equals(HranaStatus.ONHOLD)){
				porudzbine.add(por);
			}
		}
		return new ResponseEntity<>(porudzbine, HttpStatus.OK);
	}
}
