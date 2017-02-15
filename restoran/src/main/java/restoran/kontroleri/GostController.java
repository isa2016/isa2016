package restoran.kontroleri;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.osoba.Gost;
import restoran.servis.GostServis;

@RestController
@RequestMapping("/guest")
public class GostController {

	private final GostServis gostServis;
	private HttpSession httpSession;

	@Autowired
	public GostController(final HttpSession httpSession, final GostServis servis) {
		this.gostServis = servis;
		this.httpSession = httpSession;

	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			Gost guest = ((Gost) httpSession.getAttribute("korisnik"));
			return true;
		} catch (Exception e) {
			return false;
		}
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
		return gostServis.save(guest);
	}
}