package restoran.kontroleri;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Restoran;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/restoran")
public class RestoranController {

	
	private final RestoranServis restoranServis;
	//private HttpSession httpSession;

	@Autowired
	public RestoranController(final HttpSession httpSession, final RestoranServis servis) {
		this.restoranServis = servis;
		//this.httpSession = httpSession;

	}
	
	@GetMapping("/{id}")
	public Restoran findOne(@PathVariable Long id) {
		return restoranServis.findOne(id);
	}
}
