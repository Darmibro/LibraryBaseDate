package telran.library.repo;

import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.library.entities.Book;
import telran.library.entities.Reader;
import telran.library.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Integer>{
	Stream<Record> findByReturnDateNull();
	
	List<Record> findByBookIsbnAndReturnDateNull(long isbn);

	Record findByBookIsbnAndReaderIdAndReturnDateNull(long isbn, int readerId);

	@Query(value="select count(*) from records join books"
			+ "on records_book_isbn=book_isbn join readers on reader_id=readers_reader_id"
			+ "where year between :from and :to group by book_title"
			+ "order by count(*) desc limit 1", nativeQuery=true)
	long getMaxCountBook(@Param ("from") int from, @Param("to")int to);
@Query("select book.isbn from Record where reader.year "
		+ "between :from and :to group by book.isbn having count(*)=:count")
	List<Long> getBookPopular(@Param("from")int from, @Param("to")int to, @Param("count")long count);
@Query(value="select count(*) from records reader_id"
		+ "group by reader_id order by count(*) desc limit 1", nativeQuery=true)
	long getMaxCountActiveReaders();
@Query("select reader from Record "
		+ "group by reader having count(*)=:maxCount")
List<Reader> getActiveReaders(@Param("maxCount") long maxCount);



}
