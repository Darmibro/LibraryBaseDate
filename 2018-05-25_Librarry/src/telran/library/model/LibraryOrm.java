package telran.library.model;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.library.dto.AuthorDto;
import telran.library.dto.BookDto;
import telran.library.entities.Author;
import telran.library.entities.Book;
import telran.library.repo.AuthorRepository;
import telran.library.repo.BookRepository;

@Service
public class LibraryOrm implements ILibrary {
	@Autowired
	BookRepository books;
	@Autowired
	AuthorRepository authors;

	@Override
	@Transactional
	public boolean addAuther(AuthorDto author) {
		if (authors.existsById(author.getName()))
			return false;
		Author authorOrm = new Author(author.getName(), author.getCountry());
		authors.save(authorOrm);
		return true;
	}

	@Override
	@Transactional
	public boolean addBook(BookDto book) {
		if (!checkAutors(book.getAuthorsName()))
			return false;
		if (books.existsById(book.getIsbn()))
			return false;
		Book bookOrm = new Book(book.isbn, book.amount, book.title, book.cover, book.pickPeriod,
				getAuthorsNameForBook(book.getAuthorsName()));
		books.save(bookOrm);
		return true;
	}

	private List<Author> getAuthorsNameForBook(List<String> authorsName) {
		return authorsName.stream().map(x -> authors.findById(x).orElse(null)).collect(Collectors.toList());
	}

	private boolean checkAutors(List<String> authorNames) {
		for (String name : authorNames) {
			if (!authors.existsById(name)) return false;
		}
		return true;
	}

}
