package restoran.logovanje;

import java.util.NoSuchElementException;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.osoba.Gost;
import restoran.servis.GostServis;
import restoran.servis.KonobarServis;
import restoran.servis.KuvarServis;
import restoran.servis.SankerServis;

@RestController
@RequestMapping("/loginController")
public class LoginController {

	private HttpSession httpSession;

	private GostServis gostServis;
	private KuvarServis kuvarServis;
	private KonobarServis konobarServis;
	private SankerServis sankerServis;
	private JavaMailSender javaMailSender;

	@Autowired
	public LoginController(final HttpSession httpSession, final GostServis gostServis, final KuvarServis kuvarServis,
			final KonobarServis konobarServis, final SankerServis sankerServis, final JavaMailSender javaMailSender) {
		this.httpSession = httpSession;
		this.gostServis = gostServis;
		this.kuvarServis = kuvarServis;
		this.konobarServis = konobarServis;
		this.sankerServis = sankerServis;
		this.javaMailSender = javaMailSender;
	}

	@PostMapping(path = "/logIn")
	public ResponseEntity<String> logIn(@RequestBody Korisnik userInput) {
		Korisnik k = null;
		String userType = "";
		if (gostServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = gostServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			if (k.getRegistrovan().equals("1"))
				userType = "gost";
			else
				userType = "gostNijeAktiviran";
		} else if (kuvarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = kuvarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "kuvar";
		} else if (konobarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = konobarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "konobar";
		} else if (sankerServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = sankerServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "sanker";
		}
		if (k != null) {
			httpSession.setAttribute("korisnik", k);
			return new ResponseEntity<>(userType, HttpStatus.OK);
		} else
			throw new NoSuchElementException("Ne postoji");
	}

	@GetMapping(path = "/logOut")
	public void logOut() {
		httpSession.invalidate();

	}

	@GetMapping(path = "/getLoggedUser")
	public Korisnik getLoggedUser() {
		return (Korisnik) httpSession.getAttribute("korisnik");
	}

	@PostMapping(path = "/registration")
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Gost gost) {
		gost.setId(null);
		gostServis.save(gost);
		gost.setRegistrovan(Long.toString(gost.getId()));
		

		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			System.out.println("Sadfa");
			System.out.println(gost.getMail());
			mail.setTo(gost.getMail());
			mail.setFrom("isaisaija@gmail.com");
			mail.setSubject("Activation link");
			
			mail.setText("Your activation link is: http://localhost:8080/#/activation/"+gost.getId());

			javaMailSender.send(mail);
		} catch (Exception m) {
			m.printStackTrace();

		}
	}
}