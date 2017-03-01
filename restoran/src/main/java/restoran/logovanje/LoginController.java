package restoran.logovanje;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restoran.model.osoba.Gost;
import restoran.model.osoba.Konobar;
import restoran.model.osoba.Kuvar;
import restoran.model.osoba.MenadzerRestorana;
import restoran.model.osoba.MenadzerSistema;
import restoran.model.osoba.Ponudjac;
import restoran.model.osoba.Sanker;
import restoran.servis.GostServis;
import restoran.servis.KonobarServis;
import restoran.servis.KuvarServis;
import restoran.servis.MenadzerRestoranaServis;
import restoran.servis.MenadzerSistemaServis;
import restoran.servis.PonudjacServis;
import restoran.servis.SankerServis;

@RestController
@RequestMapping("/loginController")
public class LoginController {

	private HttpSession httpSession;

	private MenadzerSistemaServis mss;
	private MenadzerRestoranaServis mrs;
	private GostServis gostServis;
	private KuvarServis kuvarServis;
	private KonobarServis konobarServis;
	private SankerServis sankerServis;
	private PonudjacServis ponudjacServis;
	private JavaMailSender javaMailSender;

	@Autowired
	public LoginController(final HttpSession httpSession, final MenadzerSistemaServis mss, final GostServis gostServis,
			final KuvarServis kuvarServis, final KonobarServis konobarServis, final SankerServis sankerServis,
			final MenadzerRestoranaServis mrs, final PonudjacServis ponudjacServis, final JavaMailSender javaMailSender) {
		this.httpSession = httpSession;
		this.gostServis = gostServis;
		this.kuvarServis = kuvarServis;
		this.konobarServis = konobarServis;
		this.sankerServis = sankerServis;
		this.javaMailSender = javaMailSender;
		this.mrs = mrs;
		this.mss = mss;
		this.ponudjacServis = ponudjacServis;
	}

	@PostMapping(path = "/logIn")
	public ResponseEntity<String> logIn(@RequestBody Korisnik userInput) {
		Korisnik k = null;
		String userType = "";
		Long id = 0l;
		if (gostServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = gostServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			if (k.getRegistrovan().equals("1"))
				userType = "gost";
			else
				userType = "gostNijeAktiviran";
			id = ((Gost) k).getId();
		} else if (kuvarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = kuvarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "kuvar";
			id = ((Kuvar) k).getId();
		} else if (konobarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = konobarServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "konobar";
			id = ((Konobar) k).getId();
			System.out.println("usao1");
		} else if (sankerServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = sankerServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "sanker";
			id = ((Sanker) k).getId();
		} else if (mss.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			System.out.println("usao");
			k = mss.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "menadzerSistema";
			id = ((MenadzerSistema) k).getId();
		} else if (mrs.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = mrs.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "menadzerRestorana";
			id = ((MenadzerRestorana) k).getId();
		} else if (ponudjacServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			k = ponudjacServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword());
			userType = "ponudjac";
			id = ((Ponudjac) k).getId();
		}
		if (k != null) {
			httpSession.setAttribute("korisnik", k);
			System.out.println("sagfas:  " + k.getRegistrovan() + "     ti[: " + userType);
			if (!k.getRegistrovan().equals("0") || userType.equals("gost") || userType.equals("gostNijeAktivan"))
				return new ResponseEntity<>(userType, HttpStatus.OK);
			return new ResponseEntity<>("" + id, HttpStatus.OK);

		} else
			throw new NoSuchElementException("Ne postoji");
	}

	@PutMapping(path = "/prviLogin/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long id, @RequestBody Korisnik userInput) {
		if (mrs.findByMail(userInput.getMail()) != null) {
			MenadzerRestorana mr = mrs.findOne(id);
			mr.setId(id);
			mr.setRegistrovan("1");
			mr.setPassword(userInput.getPassword());
			mrs.save(mr);
		} else if (mss.findByMail(userInput.getMail()) != null) {
			MenadzerSistema ms = mss.findOne(id);
			ms.setId(id);
			ms.setRegistrovan("1");
			ms.setPassword(userInput.getPassword());
			mss.save(ms);
		} else if (sankerServis.findByMail(userInput.getMail()) != null) {
			Sanker sanker = sankerServis.findOne(id);
			sanker.setId(id);
			sanker.setRegistrovan("1");
			sanker.setPassword(userInput.getPassword());
			sankerServis.save(sanker);
		} else if (kuvarServis.findByMail(userInput.getMail()) != null) {
			Kuvar kuvar = kuvarServis.findOne(id);
			kuvar.setId(id);
			kuvar.setRegistrovan("1");
			kuvar.setPassword(userInput.getPassword());
			kuvarServis.save(kuvar);
		} else if (konobarServis.findByMail(userInput.getMail()) != null) {
			Konobar konobar = konobarServis.findOne(id);
			konobar.setId(id);
			konobar.setRegistrovan("1");
			konobar.setPassword(userInput.getPassword());
			konobarServis.save(konobar);
		} else  if (ponudjacServis.findByMailAndPassword(userInput.getMail(), userInput.getPassword()) != null) {
			Ponudjac ponudjac = ponudjacServis.findOne(id);
			ponudjac.setId(id);
			ponudjac.setRegistrovan("1");
			ponudjac.setPassword(userInput.getPassword());
			ponudjacServis.save(ponudjac);
		} 
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
			mail.setTo(gost.getMail());
			mail.setFrom("isaisaija@gmail.com");
			mail.setSubject("Activation link");

			mail.setText("Your activation link is: http://localhost:8080/#/activation/" + gost.getId());

			javaMailSender.send(mail);
		} catch (Exception m) {
			m.printStackTrace();

		}
	}
}