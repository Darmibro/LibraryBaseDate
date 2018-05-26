package telran.library.dto;

public class AuthorDto {
public String name;
public String country;

public AuthorDto() {}

public AuthorDto(String name, String country) {
	this.name = name;
	this.country = country;
}

public String getName() {
	return name;
}

public String getCountry() {
	return country;
}

@Override
public String toString() {
	return "AutherDto [name=" + name + ", country=" + country + "]";
}


}
