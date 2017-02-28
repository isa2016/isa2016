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

import restoran.model.osoba.Ponudjac;
import restoran.servis.PonudjacServis;


@RestController
@RequestMapping("/ponudjac")
public class PonudjacController {


	private final PonudjacServis ponudjacServis;
	private HttpSession httpSession;
    //private RestoranServis restoranServis;   
	
	@Autowired
	public PonudjacController(final HttpSession httpSession, final PonudjacServis servis) {
		this.ponudjacServis = servis;
		this.httpSession = httpSession;
		//this.restoranServis = restServis;	
	}
     
	
	@PutMapping(path = "/ponudjacUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Ponudjac update(@PathVariable Long id, @Valid @RequestBody Ponudjac ponudjac) {
	 	
		Optional.ofNullable(ponudjacServis.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		ponudjac.setId(id);
		httpSession.setAttribute("korisnik", ponudjac);
		return ponudjacServis.save(ponudjac);
	
	}
	
}
