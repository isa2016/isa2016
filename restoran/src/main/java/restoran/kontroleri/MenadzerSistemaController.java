package restoran.kontroleri;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Restoran;
import restoran.model.osoba.MenadzerRestorana;
import restoran.model.osoba.MenadzerSistema;
import restoran.servis.MenadzerRestoranaServis;
import restoran.servis.MenadzerSistemaServis;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/menadzerSistemaController")
public class MenadzerSistemaController {

	private RestoranServis rs;
	private MenadzerSistemaServis mss;
	private MenadzerRestoranaServis mrs;

	@Autowired
	public MenadzerSistemaController(final RestoranServis rs, final MenadzerSistemaServis mss, final MenadzerRestoranaServis mrs) {
		this.rs = rs;
		this.mss = mss;
		this.mrs = mrs;

	}

	@PostMapping(path = "/addRest/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@PathVariable Long id,@Valid @RequestBody Restoran rest) {
		MenadzerRestorana mr = mrs.findOne(id);
		mr.setZaposlen(1);
		rest.getMenadzeriRestorana().add(mr);
		rs.save(rest);

	}

	@GetMapping("/sviMR")
	public ResponseEntity<List<MenadzerRestorana>> findAllMR() {
		return new ResponseEntity<>(mrs.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/nezaposleni")
	public ResponseEntity<List<MenadzerRestorana>> findAllMR2() {
		List<MenadzerRestorana> lista = new ArrayList<MenadzerRestorana>();
		for(MenadzerRestorana m : mrs.findAll()){
			if(m.getZaposlen()==0){
				lista.add(m);
			}
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/sviMS")
	public ResponseEntity<List<MenadzerSistema>> findAllMS() {
		return new ResponseEntity<>(mss.findAll(), HttpStatus.OK);
	}

	@PostMapping(path = "/addMS")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveMS(@Valid @RequestBody MenadzerSistema ms) {	
		ms.setRegistrovan("1");
		mss.save(ms);
	}
	
	@PostMapping(path = "/addMR")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveMR(@Valid @RequestBody MenadzerRestorana mr) {	
		mr.setZaposlen(0);
		mr.setRegistrovan("1");
		mrs.save(mr);
	}
}
