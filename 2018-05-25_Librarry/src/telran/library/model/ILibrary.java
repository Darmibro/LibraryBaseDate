package telran.library.model;


import java.util.*;
import telran.library.dto.*;

public interface ILibrary {
LibraryReturnCode addAuthor(AuthorDto author);
LibraryReturnCode addBook(BookDto book);
LibraryReturnCode pickBook(PickBookData pickBook);
LibraryReturnCode addReader(ReaderDto reader);
LibraryReturnCode returnBook(ReturnBookData returnBookDate);
List<ReaderDto> getReadersDelayingBooks(); //readers  delaying  books
List<AuthorDto> getBookAuthors(long isbn);//authors  of  a  given  book
List<BookDto> getAuthorBooks(String authorName); //books  written  by  agiven  author
}
