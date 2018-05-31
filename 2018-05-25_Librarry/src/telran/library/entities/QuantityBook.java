package telran.library.entities;

import javax.persistence.*;

@Entity

public class QuantityBook {
	@Id
	long isbn;;
 int quantityBook;

public QuantityBook(long isbn, int quantityBook) {
	this.isbn = isbn;
	this.quantityBook = quantityBook;
}

public QuantityBook() {
}

public int getQuantityBook() {
	return quantityBook;
}

public void setQuantityBook(int quantityBook) {
	this.quantityBook = quantityBook;
}

public long getIsbn() {
	return isbn;
}


}