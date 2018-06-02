package telran.library.entities;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import telran.library.dto.BookDto;
import telran.library.dto.Cover;

@Table(name="book")
@Entity
public class Book {
	@Id
	long isbn;
	int amount;
	String title;
	@Enumerated(EnumType.STRING)
	Cover cover;
	
	int pickPeriod;
	@ManyToMany
	List<Author> authors;
	@OneToMany(mappedBy="book")
	List<Record> records;
	
	public Book() {
	}

	public Book(long isbn, int amount, String title, Cover cover, int pickPeriod, List<Author> authors) {
		this.isbn = isbn;
		this.amount = amount;
		this.title = title;
		this.cover = cover;
		this.pickPeriod = pickPeriod;
		this.authors = authors;
	}

	public long getIsbn() {
		return isbn;
	}

	public int getAmount() {
		return amount;
	}

	public String getTitle() {
		return title;
	}

	public Cover getCover() {
		return cover;
	}

	public int getPickPeriod() {
		return pickPeriod;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public List<Record> getRecords() {
		return records;
	}
	
	public BookDto getBook() {
		List<String> authorNames=authors.stream().map(x->x.getAuthor().getName()).collect(Collectors.toList());
		return new BookDto(isbn, title, amount, authorNames, cover, pickPeriod);
	}
	
	
}
