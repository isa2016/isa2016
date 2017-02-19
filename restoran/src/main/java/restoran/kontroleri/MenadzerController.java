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
import restoran.model.osoba.Kuvar;
import restoran.servis.RestoranServis;

@RestController
@RequestMapping("/menadzer")
public class MenadzerController {

	//private final MenadzerRestoranaServis menadzerRestServis;
	//private HttpSession httpSession;
    private RestoranServis restoranServis;   
	
	@Autowired
	public MenadzerController(final RestoranServis restServis) {
		//this.menadzerRestServis = servis;
		//this.httpSession = httpSession;
		this.restoranServis = restServis;	
	}
     
	@GetMapping (path = "/{id}")
	public  ResponseEntity<List<Restoran>> list(@PathVariable Long id){
		List<Restoran> l = restoranServis.findAll();
	    List<Restoran> lista = new ArrayList<Restoran>();
		for(int i=0; i<l.size();i++){
			Restoran r = l.get(i);
			for(int j=0; j< r.getMenadzeriRestorana().size(); j++){
                 if(id.equals(r.getMenadzeriRestorana().get(j).getId())){
                	 lista.add(r);
                 }
			}
		}
		
		return new ResponseEntity<>( lista,HttpStatus.OK);
		
		}
	
	

	@PostMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void dodajKonobara(@Valid @RequestBody Kuvar cook, Long id) {
		
		Restoran restaurant = restoranServis.findOne(id);
	   // restaurant.getKuvari().add(cook);
		restoranServis.save(restaurant);
	}
/*
	@PostMapping(path = "/restaurant/saveBartender")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveCook(@Valid @RequestBody Bartender bartender) {
		bartender.setRegistrated("0");
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getBartenders().add(bartender);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveBidder")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBidder(@Valid @RequestBody Bidder bidder) {
		bidder.setRegistrated("0");
		Restaurant restaurant = findRestaurantForRestaurantManager();
		restaurant.getBidders().add(bidder);
		restaurantService.save(restaurant);
	}
*/

	
	}


