package restoran.kontroleri;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Jelo;
import restoran.model.Pice;
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
	
	@GetMapping("/jelovnik/{id}")
	public ResponseEntity<List<Jelo>> findFood(@PathVariable Long id) {
		Restoran r =  restoranServis.findOne(id);		
		return new ResponseEntity<>(r.getJelovnik(), HttpStatus.OK);
		
	}
	
	@GetMapping("/pice/{id}")
	public ResponseEntity<List<Pice>> findDrinks(@PathVariable Long id) {
		Restoran r =  restoranServis.findOne(id);		
		return new ResponseEntity<>(r.getKartaPica(), HttpStatus.OK);
		
	}
	
	
}
