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
import restoran.model.osoba.Sanker;
import restoran.servis.KonobarServis;
import restoran.servis.SankerServis;

@RestController
@RequestMapping("/sanker")
public class SankerController {
	private final SankerServis ss;
	private HttpSession session;
	
	@Autowired
	public SankerController(final SankerServis ss, HttpSession session){
		this.ss = ss;
		this.session = session;
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

}
