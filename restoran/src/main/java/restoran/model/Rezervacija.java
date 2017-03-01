package restoran.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import restoran.model.osoba.Gost;

@Data
@Entity
public class Rezervacija {

	@Id
	@GeneratedValue
	@Column(name = "Rezervacija_ID")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Restoran restaurant;
	
	@Column
	private String date;
	
	@Column
	private int hours;
	
	@Column
	private int minutes;
	
	@Column
	private Double duration;
	
	@ManyToMany
	@JoinTable(name = "Rezervacije", joinColumns = @JoinColumn(name = "Rezervacija_ID"), inverseJoinColumns = @JoinColumn(name = "Gost_ID"))
	private List<Gost> gosti;
	
	@ManyToMany
	@JoinTable(name = "Pozvani", joinColumns = @JoinColumn(name = "Rezervacija_ID"), inverseJoinColumns = @JoinColumn(name = "Gost_ID"))
	private List<Gost> pozvani;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Rezervacija_porudzbina", joinColumns = @JoinColumn(name = "Rezervacija_ID"), inverseJoinColumns = @JoinColumn(name = "Porudzbina_ID"))
	private List<Porudzbina> porudzbine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public List<Gost> getGosti() {
		return gosti;
	}

	public void setGosti(List<Gost> gosti) {
		this.gosti = gosti;
	}

	public Restoran getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restoran restaurant) {
		this.restaurant = restaurant;
	}

	public Rezervacija() {
		super();
		this.gosti = new ArrayList<Gost>();
	}

	public List<Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public void setPorudzbine(List<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}

	public List<Gost> getPozvani() {
		return pozvani;
	}

	public void setPozvani(List<Gost> pozvani) {
		this.pozvani = pozvani;
	}
	
	
	
}
