package cl.barucvilla.EY.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "number")
	private int number;

	@Column(name = "citycode")
	private int citycode;

	@Column(name = "contrycode")
	private int contrycode;

	public Phone() {
		super();
	}

	public Phone(int number, int citycode, int contrycode, User user) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getContrycode() {
		return contrycode;
	}

	public void setContrycode(int contrycode) {
		this.contrycode = contrycode;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode
				+ ", user=" + "]";
	}

}
