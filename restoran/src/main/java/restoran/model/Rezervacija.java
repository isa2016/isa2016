package restoran.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Rezervacija {

	@Id
	@GeneratedValue
	@Column(name = "Rezervacija_ID")
	private Long id;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	private Restoran restaurant;*/
	
	@Column
	private String date;
	
	@Column
	private int hours;
	
	@Column
	private int minutes;
	
	@Column
	private Double duration;
	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Rezervacije", joinColumns = @JoinColumn(name = "Rezervacija_ID"), inverseJoinColumns = @JoinColumn(name = "Gost_ID"))
	private List<Gost> gosti;*/

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

	/*public List<Gost> getGosti() {
		return gosti;
	}

	public void setGosti(List<Gost> gosti) {
		this.gosti = gosti;
	}*/

	/*public Restoran getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restoran restaurant) {
		this.restaurant = restaurant;
	}*/
	
	
	
}
