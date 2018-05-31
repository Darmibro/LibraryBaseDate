package telran.library.entities;

import java.util.List;

import javax.persistence.*;

@Table(name="readers")
@Entity
public class Reader {
	@Id
	int id;
	String name;
	int year;
	int phone;
	@OneToMany(mappedBy="reader")
	List<Record> records;
	
	public Reader() {
	}
	
	public Reader(int id, String name, int year, int phone) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public int getYear() {
		return year;
	}
	
	
}
