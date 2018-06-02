package telran.library.entities;
import java.time.LocalDate;

import javax.persistence.*;
@Table(name="records")
@Entity
public class Record {
@Id
@GeneratedValue
int id;

LocalDate pickDate;
LocalDate returnDate;
int delayDays;

@ManyToOne
Reader reader;
@ManyToOne
Book book;


public Record() {
}

public Book getBook() {
	return book;
}

public Record(LocalDate pickDate, Reader reader, Book book) {
	this.pickDate = pickDate;
	this.reader = reader;
	this.book = book;
	
}

public int getId() {
	return id;
}

public LocalDate getPickDate() {
	return pickDate;
}

public LocalDate getReturnData() {
	return returnDate;
}

public int getDelayDays() {
	return delayDays;
}

public Reader getReader() {
	return reader;
}

public LocalDate getReturnDate() {
	return returnDate;
}

public void setReturnDate(LocalDate returnDate) {
	this.returnDate = returnDate;
}

public void setDelayDays(int delayDays) {
	this.delayDays = delayDays;
}

}
