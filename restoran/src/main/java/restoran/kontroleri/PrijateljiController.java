package restoran.kontroleri;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.enumeracije.PrijateljstvoStatus;
import restoran.model.osoba.Gost;
import restoran.model.osoba.Prijatelji;
import restoran.servis.GostServis;
import restoran.servis.PrijateljiServis;

@RestController
@RequestMapping("/prijatelji")
public class PrijateljiController {

	private final PrijateljiServis prijateljiServis;
	private final GostServis gostServis;
	private HttpSession httpSession;

	@Autowired
	public PrijateljiController(final PrijateljiServis prijateljiServis, final GostServis gostServis,
			final HttpSession httpSession) {
		this.prijateljiServis = prijateljiServis;
		this.httpSession = httpSession;
		this.gostServis = gostServis;

	}

	@GetMapping("/primljeni")
	public ResponseEntity<List<Gost>> primljeniZahtevi() {

		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		List<Gost> lista = new ArrayList<>();

		List<Prijatelji> p = prijateljiServis.findByPrimioZahtev(gost);
		for (Prijatelji pp : p) {
			if (pp.getStatus() == PrijateljstvoStatus.naCekanju) {
				lista.add(pp.getPoslaoZahtev());
			}
		}

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/prihvaceni")
	public ResponseEntity<List<Gost>> prihvaceni() {

		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		List<Gost> lista = new ArrayList<>();

		List<Prijatelji> p = prijateljiServis.findByPrimioZahtev(gost);
		for (Prijatelji pp : p) {
			if (pp.getStatus() == PrijateljstvoStatus.prihvacen) {
				lista.add(pp.getPoslaoZahtev());
			}
		}

		List<Prijatelji> p2 = prijateljiServis.findByPoslaoZahtev(gost);
		for (Prijatelji pp : p2) {
			if (pp.getStatus() == PrijateljstvoStatus.prihvacen) {
				lista.add(pp.getPrimioZahtev());
			}
		}
		lista.stream().sorted((object1, object2) -> object1.getIme().compareTo(object2.getIme()));

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/poslati")
	public ResponseEntity<List<Gost>> poslatiZahtevi() {

		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		List<Gost> lista = new ArrayList<>();

		List<Prijatelji> p = prijateljiServis.findByPoslaoZahtev(gost);
		for (Prijatelji pp : p) {
			if (pp.getStatus() == PrijateljstvoStatus.naCekanju) {
				lista.add(pp.getPrimioZahtev());
			}
		}

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@PostMapping(path = "prihvati/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void prihvatiZahtev(@PathVariable Long id) {
		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		Gost gost2 = gostServis.findOne(id);

		Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost, gost2);
		p.setStatus(PrijateljstvoStatus.prihvacen);
		prijateljiServis.save(p);
	}

	@PostMapping(path = "odbij/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void odbijZahtev(@PathVariable Long id) {
		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		Gost gost2 = gostServis.findOne(id);

		Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost, gost2);
		// p.setStatus(PrijateljstvoStatus.odbijen);
		prijateljiServis.delete(p.getId());
	}

	@PostMapping(path = "obrisi/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void obrisiPrijatelja(@PathVariable Long id) {
		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		Gost gost2 = gostServis.findOne(id);

		Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost, gost2);
		Prijatelji p2 = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost2, gost);
		try {
			prijateljiServis.delete(p.getId());
		} catch (Exception e) {
			prijateljiServis.delete(p2.getId());
		}
	}

	@PostMapping(path = "dodaj/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajPrijatelja(@PathVariable Long id) {
		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		Gost gost2 = gostServis.findOne(id);

		Prijatelji p = new Prijatelji(gost, gost2, PrijateljstvoStatus.naCekanju);
		prijateljiServis.save(p);
	}

	@GetMapping("/poImenu/{ime}")
	public ResponseEntity<List<Gost>> pronadjiPoImenu(@PathVariable String ime) {
		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		List<Gost> list = gostServis.findByIme(ime);
		List<Gost> list2 = list;

		for (Gost g : list) {
			Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(g, gost);
			if (p != null) {
				list2.remove(g);
			}
		}

		for (Gost g : list) {
			Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost, g);
			if (p != null) {
				list2.remove(g);
			}
		}
		if (list2.size() > 0) {
			return new ResponseEntity<>(list2, HttpStatus.OK);
		} else
			throw new NoSuchElementException("Ne postoji");
	}

	@GetMapping("/poPrezimenu/{prezime}")
	public ResponseEntity<List<Gost>> pronadjiPoPrezimenu(@PathVariable String prezime) {

		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		List<Gost> list = gostServis.findByPrezime(prezime);
		List<Gost> list2 = list;

		for (Gost g : list) {
			Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(g, gost);
			if (p != null) {
				list2.remove(g);
			}
		}

		for (Gost g : list) {
			Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost, g);
			if (p != null) {
				list2.remove(g);
			}
		}
		if (list2.size() > 0) {
			return new ResponseEntity<>(list2, HttpStatus.OK);
		} else
			throw new NoSuchElementException("Ne postoji");
	}

	@GetMapping("/pronadji/{ime}/{prezime}")
	public ResponseEntity<List<Gost>> pronadji(@PathVariable String ime, @PathVariable String prezime) {

		Gost gost = ((Gost) httpSession.getAttribute("korisnik"));
		List<Gost> list = gostServis.findByImeAndPrezime(ime, prezime);
		List<Gost> list2 = list;

		for (Gost g : list) {
			Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(g, gost);
			if (p != null) {
				list2.remove(g);
			}
		}

		for (Gost g : list) {
			Prijatelji p = prijateljiServis.findByPrimioZahtevAndPoslaoZahtev(gost, g);
			if (p != null) {
				list2.remove(g);
			}
		}
		if (list2.size() > 0) {
			return new ResponseEntity<>(list2, HttpStatus.OK);
		} else
			throw new NoSuchElementException("Ne postoji");
	}

	@GetMapping("/prijatelji")
	public ResponseEntity<List<Gost>> sviPrijatelji() {
		List<Gost> lista = new ArrayList<>();
		Gost g = ((Gost) httpSession.getAttribute("korisnik"));
		List<Prijatelji> p = prijateljiServis.findByPoslaoZahtev(g);
		List<Prijatelji> p2 = prijateljiServis.findByPrimioZahtev(g);

		for (Prijatelji pp : p) {
			if (pp.getStatus() == PrijateljstvoStatus.prihvacen) {
				lista.add(pp.getPrimioZahtev());
			}
		}

		for (Prijatelji pp : p2) {
			if (pp.getStatus() == PrijateljstvoStatus.prihvacen) {
				lista.add(pp.getPoslaoZahtev());
			}
		}

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/broj")
	public ResponseEntity<Integer> brojZahteva() {

		Gost g = ((Gost) httpSession.getAttribute("korisnik"));
		int x = 0;

		for (Prijatelji p : prijateljiServis.findByPrimioZahtev(g)) {
			if (p.getStatus().equals(PrijateljstvoStatus.naCekanju)) {
				x++;
			}
		}
		return new ResponseEntity<>(x, HttpStatus.OK);
	}

}
