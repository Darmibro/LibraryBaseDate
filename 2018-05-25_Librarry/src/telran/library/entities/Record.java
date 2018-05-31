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
LocalDate returnData;
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
	return returnData;
}

public int getDelayDays() {
	return delayDays;
}

public Reader getReader() {
	return reader;
}

}
