package restoran.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import restoran.model.osoba.Gost;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue
	@Column(name = "Rezervacija_ID")
	private Long id;
	
	@Column
	private Date date;
	
	@Column
	private int hours;
	
	@Column
	private int minutes;
	
	@Column
	private Double duration;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Rezervacije", joinColumns = @JoinColumn(name = "Rezervacija_ID"), inverseJoinColumns = @JoinColumn(name = "Gost_ID"))
	private List<Gost> gosti;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
	
	
	
}
