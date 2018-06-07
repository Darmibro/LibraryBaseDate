package telran.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.entities.Reader;
import telran.library.entities.Record;

public interface ReaderRepository extends JpaRepository<Reader, Integer>{


	
}
