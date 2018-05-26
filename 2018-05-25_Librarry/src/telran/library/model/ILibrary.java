package telran.library.model;

import telran.library.dto.AuthorDto;
import telran.library.dto.BookDto;

public interface ILibrary {
boolean addAuther(AuthorDto auther);
boolean addBook(BookDto book);
}
