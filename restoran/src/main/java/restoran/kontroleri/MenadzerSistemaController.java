package restoran.kontroleri;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Restoran;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/menadzerSistemaController")
public class MenadzerSistemaController {

	private RestoranServis rs;
	
	@Autowired
	public MenadzerSistemaController(final RestoranServis rs) {
		this.rs = rs;
	}
	
	@PostMapping(path = "/addRest")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Restoran rest) {
		
		rs.save(rest);
		
	}
}
