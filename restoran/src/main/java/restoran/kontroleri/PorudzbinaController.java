package restoran.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Porudzbina;
import restoran.servis.PorudzbinaServis;

@RestController
@RequestMapping("/Porudzbina")
public class PorudzbinaController {

	
	private final PorudzbinaServis PorudzbinaServis;

	@Autowired
	public PorudzbinaController( final PorudzbinaServis servis) {
		this.PorudzbinaServis = servis;

	}
	
	@GetMapping("/{id}")
	public Porudzbina findOne(@PathVariable Long id) {
		
		return PorudzbinaServis.findOne(id);
	}
}
