package telran.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.library.dto.AuthorDto;
import telran.library.dto.BookDto;
import telran.library.dto.LibraryApiConstants;
import telran.library.dto.LibraryReturnCode;
import telran.library.dto.PickBookData;
import telran.library.dto.ReaderDto;
import telran.library.dto.ReturnBookData;
import telran.library.model.ILibrary;

@ComponentScan(basePackages = "telran.library.model")
@EnableJpaRepositories("telran.library.repo")
@EntityScan("telran.library.entities")
@RestController
public class LibraryRestController {
	@Autowired
	ILibrary library;

	@PostMapping(value = LibraryApiConstants.ADD_AUTHOR)
	LibraryReturnCode addAuther(@RequestBody AuthorDto author) {
		return library.addAuthor(author);
	}

	@PostMapping(value = LibraryApiConstants.ADD_BOOK)
	LibraryReturnCode addBook(@RequestBody BookDto book) {
		return library.addBook(book);
	}

	@PostMapping(value = LibraryApiConstants.ADD_READER)
	LibraryReturnCode addReader(@RequestBody ReaderDto reader) {
		return library.addReader(reader);
	}

	@PostMapping(value = LibraryApiConstants.PICK_BOOK)
	LibraryReturnCode pickBook(@RequestBody PickBookData pickBookData) {
		return library.pickBook(pickBookData);
	}

	@GetMapping(value = LibraryApiConstants.RETURN_BOOK)
	LibraryReturnCode returnBook(ReturnBookData returnBookDate) {
		return library.returnBook(returnBookDate);
	}

	@GetMapping(value = LibraryApiConstants.READERS_DELAYING_BOOK)
	List<ReaderDto> getreadersDelayingBooks() {
		return library.getReadersDelayingBooks();
	}

	@GetMapping(value = LibraryApiConstants.BOOK_AUTHORS)
	List<AuthorDto> getBookAuthors(long isbn) {
		return library.getBookAuthors(isbn);
	}

	@GetMapping(value = LibraryApiConstants.AUTHOR_BOOKS)
	List<BookDto> getAuthorBooks(String authorName) {
		return library.getAuthorBooks(authorName);
	}
}
