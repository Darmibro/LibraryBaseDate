package telran.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.library.dto.AuthorDto;
import telran.library.dto.BookDto;
import telran.library.model.ILibrary;

@ComponentScan(basePackages = "telran.library.model")
@EnableJpaRepositories("telran.library.repo")
@EntityScan("telran.library.entities")
@RestController
public class LibraryRestController {
	@Autowired
	ILibrary library;

	@PostMapping(value = "add_auther")
	boolean addAuther(@RequestBody AuthorDto auther) {
		return library.addAuther(auther);
	}

	@PostMapping(value = "add_book")
	boolean addBook(@RequestBody BookDto book) {
		return library.addBook(book);
	}

}
