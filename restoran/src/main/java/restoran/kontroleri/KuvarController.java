package restoran.kontroleri;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.servis.KonobarServis;
import restoran.servis.KuvarServis;

@RestController
@RequestMapping("/kuvar")
public class KuvarController {
	private final KuvarServis ks;
	private HttpSession session;
	
	@Autowired
	public KuvarController(final KuvarServis ks, HttpSession session){
		this.ks = ks;
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
}
