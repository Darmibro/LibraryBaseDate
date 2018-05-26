package telran.library.dto;

import java.util.List;

public class BookDto {

	public long isbn;
	public String title;
	public int amount;
	public List<String> authorsName;
	public Cover cover;
	public int pickPeriod;
	
	public BookDto() {}

	public BookDto(long isbn, int amount, String title,  Cover cover, int pickPeriod,  List<String> authorsName) {
		this.isbn = isbn;
		this.title = title;
		this.amount = amount;
		this.authorsName = authorsName;
		this.cover = cover;
		this.pickPeriod = pickPeriod;
	}

	public long getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public int getAmount() {
		return amount;
	}

	public List<String> getAuthorsName() {
		return authorsName;
	}

	public Cover getCover() {
		return cover;
	}

	public int getPickPeriod() {
		return pickPeriod;
	}

	@Override
	public String toString() {
		return "BookDto [isbn=" + isbn + ", title=" + title + ", amount=" + amount + ", authorsName=" + authorsName
				+ ", cover=" + cover + ", pickPeriod=" + pickPeriod + "]";
	}
	
	
}