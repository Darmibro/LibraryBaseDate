package telran.library.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.library.dto.*;
import telran.library.entities.*;
import telran.library.repo.*;

@Service
public class LibraryOrm implements ILibrary {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	@Autowired
	BookRepository books;
	@Autowired
	AuthorRepository authors;
	@Autowired
	ReaderRepository readers;
	@Autowired
	RecordRepository records;
	@Autowired
	QuantityBookRepository quantitys;

	@Override
	@Transactional
	public LibraryReturnCode addAuthor(AuthorDto author) {
		if (authors.existsById(author.getName()))
			return LibraryReturnCode.AUTHOR_ALREADY_EXISTS;
		Author authorOrm = new Author(author.getName(), author.getCountry());
		authors.save(authorOrm);
		return LibraryReturnCode.OK;
	}

	@Override
	@Transactional
	public LibraryReturnCode addBook(BookDto book) {
		if (books.existsById(book.isbn)){
			return LibraryReturnCode.BOOK_ALREADY_EXISTS;
		}
		
		if(!checkAutors(book.getAuthorNames())) {
			return LibraryReturnCode.NO_AUTHOR;
		}
		Book bookOrm= new Book(book.isbn, book.amount, book.title, book.cover, book.pickPeriod, 
				getAuthorsNameForBook(book.getAuthorNames()));
		books.save(bookOrm);
		return LibraryReturnCode.OK;
	}

	private List<Author> getAuthorsNameForBook(List<String> authorNames) {
		return authorNames.stream().map(x -> authors.findById(x).get()).collect(Collectors.toList());
	}

	private boolean checkAutors(List<String> authorNames) {
		for (String name : authorNames) {
			if (!authors.existsById(name))
				return false;
		}
		return true;
	}

	@Override
	@Transactional
	public LibraryReturnCode pickBook(PickBookData pickBook) {
		if (!readers.existsById(pickBook.getReaderId())) {
			return LibraryReturnCode.NO_READER;
		}
		if (!books.existsById(pickBook.getIsbn())) {
			return LibraryReturnCode.NO_BOOK;
		}

		LocalDate pickDate = getDateOrm(pickBook.getPickDate());
		if (pickDate == null) {
			return LibraryReturnCode.WRONG_DATA_fORMAT;
		}
		Book bookOrm = books.findById(pickBook.getIsbn()).get();

		if (getCountBookOrm(bookOrm) == 0) {
			return LibraryReturnCode.ALL_BOOKS_IN_USE;
		}

		Record recordOrm = new Record(pickDate, readers.findById(pickBook.getReaderId()).get(), bookOrm);
		records.save(recordOrm);

		QuantityBook qBook = quantitys.findById(bookOrm.getIsbn()).get();
		qBook.setQuantityBook(getCountBookOrm(bookOrm) - 1);
		quantitys.save(qBook);

		return LibraryReturnCode.OK;
	}

	private int getCountBookOrm(Book bookOrm) {
		QuantityBook quantityBook = quantitys.findById(bookOrm.getIsbn()).orElse(null);
		if (quantityBook != null) {
			return quantityBook.getQuantityBook();
		}
		quantityBook = new QuantityBook(bookOrm.getIsbn(), bookOrm.getAmount());
		return quantityBook.getQuantityBook();
	}

	private LocalDate getDateOrm(String pickDate) {
		try {
			return LocalDate.parse(pickDate, DateTimeFormatter.ofPattern(DATE_FORMAT));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public LibraryReturnCode addReader(ReaderDto reader) {
		if (readers.existsById(reader.getId()))
			return LibraryReturnCode.READER_ALREADY_EXISTS;
		Reader readerOrm = new Reader(reader.getId(), reader.getName(), reader.getYear(), reader.getPhone());
		readers.save(readerOrm);
		return LibraryReturnCode.OK;
	}

	@Override
	@Transactional
	public LibraryReturnCode returnBook(ReturnBookData returnBookDate) {
	//	List<Record> recordsOrm=records.findByBookIsbnAndReturnDateNull(isbn);
		Record recordReturnOrm=records.findByBookIsbnAndReaderIdAndReturnDateNull(returnBookDate.getIsbn(),returnBookDate.getReaderId());
		if(recordReturnOrm==null) {
			return LibraryReturnCode.NO_RECORD_FOR_RETURN;
		}
		LocalDate returnDate=getDateOrm(returnBookDate.getReturnData());
		recordReturnOrm.setReturnDate(returnDate);
		
		int useDaysBook=(int) ChronoUnit.DAYS.between(recordReturnOrm.getPickDate(), returnDate);
		int pickPeriodBook=books.findById(returnBookDate.getIsbn()).get().getPickPeriod();
		if (pickPeriodBook< useDaysBook) {
			recordReturnOrm.setDelayDays(useDaysBook-pickPeriodBook);
		}
		
		return LibraryReturnCode.OK;
	}

	@Override
	public List<ReaderDto> getReadersDelayingBooks() {
		
		return null;
	}

	@Override
	public List<AuthorDto> getBookAuthors(long isbn) {
		List<Author>authorNamesBook=books.findById(isbn).get().getAuthors();
		return authorNamesBook.stream().map(x->x.getAuthor()).collect(Collectors.toList());
	}

	@Override
	public List<BookDto> getAuthorBooks(String authorName) {
		List<Book> authorBooks=books.findByAuthors(authorName);
		return authorBooks.stream().map(x->x.getBook()).collect(Collectors.toList());
	}

}
