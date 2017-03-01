package restoran.kontroleri;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Jelo;
import restoran.model.ObjavaPonude;
import restoran.model.Pice;
import restoran.model.Restoran;
import restoran.model.Sto;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.Ponudjac;
import restoran.model.osoba.Sanker;
import restoran.servis.JeloServis;
import restoran.servis.ObjavaPonudeServis;
import restoran.servis.PiceServis;
import restoran.servis.PonudjacServis;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/restoran")
public class RestoranController {

	private final RestoranServis restoranServis;
	private final PonudjacServis pServis;
	private final JeloServis jeloServis;
	private final PiceServis piceServis;
	private final ObjavaPonudeServis objavaPonudeServis;
	private final PonudjacServis ponudjacServis;

	@Autowired
	public RestoranController(final RestoranServis servis, final PonudjacServis pServis, final JeloServis jServis,
			final ObjavaPonudeServis objavaPonude, final PiceServis piceServis, final PonudjacServis ponudjacServis) {
		this.restoranServis = servis;
		this.pServis = pServis;
		this.jeloServis = jServis;
		this.objavaPonudeServis = objavaPonude;
		this.piceServis = piceServis;
		this.ponudjacServis = ponudjacServis;
	}

	@GetMapping("/{id}")
	public Restoran findOne(@PathVariable Long id) {
		return restoranServis.findOne(id);
	}

	@GetMapping("/jelovnik/{id}")
	public ResponseEntity<List<Jelo>> findFood(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getJelovnik(), HttpStatus.OK);

	}

	@GetMapping("/pice/{id}")
	public ResponseEntity<List<Pice>> findDrinks(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getKartaPica(), HttpStatus.OK);

	}

	@GetMapping("/kuvari/{id}")
	public ResponseEntity<List<Kuvar>> findCooks(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getKuvari(), HttpStatus.OK);

	}

	@GetMapping("/konobari/{id}")
	public ResponseEntity<List<Konobar>> findWaiters(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getKonobari(), HttpStatus.OK);

	}

	@GetMapping("/sankeri/{id}")
	public ResponseEntity<List<Sanker>> findBartenders(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getSankeri(), HttpStatus.OK);

	}

	@GetMapping("/ponudjaci/{id}")
	public ResponseEntity<List<Ponudjac>> findPonudjaci(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getPonudjaci(), HttpStatus.OK);

	}

	@GetMapping("/ponudjaci2/{id}")
	public ResponseEntity<List<Ponudjac>> findPonudjaci2(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);

		List<Ponudjac> lista = new ArrayList<Ponudjac>();
		for (Ponudjac p : pServis.findAll()) {
			if (!r.getPonudjaci().contains(p)) {
				lista.add(p);
			}
		}

		return new ResponseEntity<>(lista, HttpStatus.OK);

	}

	@GetMapping("/stolovi/{id}")
	public ResponseEntity<List<Sto>> nadjiStolove(@PathVariable Long id) {
		Restoran r = restoranServis.findOne(id);
		return new ResponseEntity<>(r.getStolovi(), HttpStatus.OK);

	}

	@GetMapping("/napraviO/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ObjavaPonude> dodavanjeObjave(@PathVariable Long id) {

		Jelo j = jeloServis.findOne(id);
		ObjavaPonude o = new ObjavaPonude();

		System.out.println(j.getNaziv());
		System.out.println(o);

		o.getHrana().add(j);
		objavaPonudeServis.save(o);

		return new ResponseEntity<>(o, HttpStatus.OK);

	}

	@GetMapping("/napraviOb/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ObjavaPonude> dodavanjeObjave2(@PathVariable Long id) {

		Pice p = piceServis.findOne(id);
		ObjavaPonude o = new ObjavaPonude();

		o.getPice().add(p);
		objavaPonudeServis.save(o);

		return new ResponseEntity<>(o, HttpStatus.OK);

	}

	@GetMapping("/objavaJelo/{id}/{id2}")
	public ResponseEntity<ObjavaPonude> dodajJ(@PathVariable Long id, @PathVariable Long id2) {
		Jelo j = jeloServis.findOne(id);
		ObjavaPonude o = objavaPonudeServis.findOne(id2);
		o.getHrana().add(j);
		objavaPonudeServis.save(o);

		return new ResponseEntity<>(o, HttpStatus.OK);
	}

	@GetMapping("/objavaPice/{id}/{id2}")
	public ResponseEntity<ObjavaPonude> dodajP(@PathVariable Long id, @PathVariable Long id2) {
		Pice p = piceServis.findOne(id);
		ObjavaPonude o = objavaPonudeServis.findOne(id2);
		o.getPice().add(p);
		objavaPonudeServis.save(o);

		return new ResponseEntity<>(o, HttpStatus.OK);
	}

	@PostMapping(path = "/restoranii/objavaPosalji/{id}/{pocetak}/{kraj}/{id2}")
	@ResponseStatus(HttpStatus.CREATED)
	public void potvrdaRezervacije2(@PathVariable Long id, @PathVariable String pocetak, @PathVariable String kraj,
			@PathVariable Long id2) throws ParseException {

		ObjavaPonude o = objavaPonudeServis.findOne(id);
		/*
		 * DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * Date date = format.parse(pocetak); Date date2 = format.parse(kraj);
		 */

		System.out.println("AJDEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		o.setPocetakVazenja(pocetak);
		o.setKrajaVazenja(kraj);
		o.setRestoranID(id2);

		objavaPonudeServis.save(o);

		Restoran r = restoranServis.findOne(id2);

		List<Ponudjac> pon = r.getPonudjaci();

		for (int i = 0; i < r.getPonudjaci().size(); i++) {
			r.getPonudjaci().get(i).getObjave().add(o);
			ponudjacServis.save(r.getPonudjaci().get(i));
			System.out.println(pon.get(i).getObjave().get(0).getId() + "aaaaaaaaaaaaaaaaaaaa");
		}

	}

}
