package telran.library.dto;

public class ReaderDto {
	int id;
	String name;
	int year;
	int phone;

	public ReaderDto() {
	}

	public ReaderDto(int id, String name, int year, int phone) {
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

	@Override
	public String toString() {
		return "ReaderDto [id=" + id + ", name=" + name + ", year=" + year + ", phone=" + phone + "]";
	}

}
