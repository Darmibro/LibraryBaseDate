package telran.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Integer>{

	List<Record> findByBookIsbnAndReturnDateNull(long isbn);

	Record findByBookIsbnAndReaderIdAndReturnDateNull(long isbn, int readerId);

}
