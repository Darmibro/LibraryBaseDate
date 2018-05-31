package telran.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.library.entities.QuantityBook;

public interface QuantityBookRepository extends JpaRepository<QuantityBook, Long>{

}
