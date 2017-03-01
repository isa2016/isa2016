package restoran.kontroleri;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.Porudzbina;
import restoran.model.Rezervacija;
import restoran.model.osoba.Gost;
import restoran.servis.JeloServis;
import restoran.servis.PiceServis;
import restoran.servis.RestoranServis;
import restoran.servis.RezervacijaServis;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

	
	private final RezervacijaServis rezervacijaServis;
	private HttpSession httpSession;
	private final RestoranServis restoranServis;
	private final PiceServis piceServis;
	private final JeloServis jeloServis;

	@Autowired
	public RezervacijaController(final HttpSession httpSession, final RezervacijaServis servis, final RestoranServis rservis, final PiceServis pservis, final JeloServis jservis) {
		this.rezervacijaServis = servis;
		this.httpSession = httpSession;
		this.restoranServis = rservis;
		this.piceServis = pservis;
		this.jeloServis = jservis;
	}
	
	@GetMapping("/{id}")
	public Porudzbina findOne(@PathVariable Long id) {
		
		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		
		Rezervacija r = rezervacijaServis.findOne(id);
		Porudzbina p1 = null;
		
		for(Porudzbina p : r.getPorudzbine()){
			if(p.getGost().getId().equals(gostID)){
				p1=p;
			}
		}
		
		return p1;
	}
	
	@PostMapping(path = "/{id}/{ocenee}")
	@ResponseStatus(HttpStatus.CREATED)
	public void setOcena(@PathVariable Long id,@PathVariable String ocenee) {
		
		int ocena = Integer.parseInt(ocenee);
		
		
		Long gostID = ((Gost) httpSession.getAttribute("korisnik")).getId();
		
		Rezervacija r = rezervacijaServis.findOne(id);
		Porudzbina p1 = null;
		
		for(Porudzbina p : r.getPorudzbine()){
			if(p.getGost().getId().equals(gostID)){
				p1=p;
			}
		}
    
		
		for(int i=0; i<p1.getHrana().size();i++){
		  Long je = p1.getHrana().get(i).getId();
		 	
			for(int p=0;p<r.getRestaurant().jelovnik.size();p++){
				if(je.equals(r.getRestaurant().jelovnik.get(p).getId())){
					
					double oc = (r.getRestaurant().jelovnik.get(p).getOcena() * r.getRestaurant().jelovnik.get(p).getBrojac() + ocena) / (r.getRestaurant().jelovnik.get(p).getBrojac()+1);
					r.getRestaurant().jelovnik.get(p).setBrojac(r.getRestaurant().jelovnik.get(p).getBrojac()+1);
					r.getRestaurant().jelovnik.get(p).setOcena(oc);
					jeloServis.save(r.getRestaurant().jelovnik.get(p));
				}
			}
		}
		
		for(int i=0; i<p1.getPice().size();i++){
			  Long pi = p1.getPice().get(i).getId();
			 	
				for(int p=0;p<r.getRestaurant().kartaPica.size();p++){
					if(pi.equals(r.getRestaurant().kartaPica.get(p).getId())){
						
						double oce = (r.getRestaurant().kartaPica.get(p).getOcena() * r.getRestaurant().kartaPica.get(p).getBroj() + ocena) / (r.getRestaurant().kartaPica.get(p).getBroj()+1);
						r.getRestaurant().kartaPica.get(p).setBroj(r.getRestaurant().kartaPica.get(p).getBroj()+1);
						r.getRestaurant().kartaPica.get(p).setOcena(oce);	
					    piceServis.save(r.getRestaurant().kartaPica.get(p));
					}
				}
			}
		   
		    double ocen = ((r.getRestaurant().getOcena() * r.getRestaurant().getBr()) + ocena) / (r.getRestaurant().getBr()+1);
		    r.getRestaurant().setBr(r.getRestaurant().getBr()+1);
		    r.getRestaurant().setOcena((int) ocen);
		    
		    restoranServis.save(r.getRestaurant());
	}
}
