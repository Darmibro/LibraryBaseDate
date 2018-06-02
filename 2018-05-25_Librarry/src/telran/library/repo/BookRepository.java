package telran.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthors(String authorName);

}
