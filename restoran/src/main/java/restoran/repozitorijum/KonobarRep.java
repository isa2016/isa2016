package restoran.repozitorijum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import restoran.model.osoba.Konobar;

@Repository
public interface KonobarRep extends JpaRepository<Konobar, Long> {

	public Konobar findById(Long id);

	public Konobar findByMailAndPassword(String mail, String password);

	public Konobar findByMail(String mail);

}
