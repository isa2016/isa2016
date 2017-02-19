package restoran.kontroleri;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Restoran;
import restoran.model.osoba.MenadzerSistema;
import restoran.servis.MenadzerSistemaServis;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/menadzerSistemaController")
public class MenadzerSistemaController {

	private RestoranServis rs;
	private MenadzerSistemaServis mss;

	@Autowired
	public MenadzerSistemaController(final RestoranServis rs, final MenadzerSistemaServis mss) {
		this.rs = rs;
		this.mss = mss;

	}

	@PostMapping(path = "/addRest")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Restoran rest) {

		rs.save(rest);

	}

	@GetMapping("/menadzeri")
	public ResponseEntity<List<MenadzerSistema>> findAllMS() {
		return new ResponseEntity<>(mss.findAll(), HttpStatus.OK);
	}

	@PostMapping(path = "/addMS")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveMS(@Valid @RequestBody MenadzerSistema ms) {
		System.out.println("udjees");
		mss.save(ms);
	}
}
