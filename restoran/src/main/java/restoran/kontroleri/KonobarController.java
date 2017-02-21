package restoran.kontroleri;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.osoba.Gost;
import restoran.model.osoba.Konobar;
import restoran.servis.KonobarServis;

@RestController
@RequestMapping("/konobar")
public class KonobarController {

	private final KonobarServis ks;
	private HttpSession session;
	
	@Autowired
	public KonobarController(final KonobarServis ks, HttpSession session){
		this.ks = ks;
		this.session = session;
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
}
