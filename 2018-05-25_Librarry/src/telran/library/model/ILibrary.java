package telran.library.model;

import java.time.LocalDate;

import telran.library.dto.AuthorDto;
import telran.library.dto.BookDto;
import telran.library.dto.LibraryReturnCode;
import telran.library.dto.PickBookData;
import telran.library.dto.ReaderDto;

public interface ILibrary {
LibraryReturnCode addAuthor(AuthorDto auther);
LibraryReturnCode addBook(BookDto book);
LibraryReturnCode pickBook(PickBookData pickBook);
LibraryReturnCode addReader(ReaderDto reader);
}
