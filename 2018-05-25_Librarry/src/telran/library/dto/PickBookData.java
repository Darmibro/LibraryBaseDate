package telran.library.dto;

public class PickBookData {
int readerId;
long isbn;
String pickDate;

public PickBookData() {
}

public PickBookData(int readerId, long isbn, String pickDate) {
	this.readerId = readerId;
	this.isbn = isbn;
	this.pickDate = pickDate;
}

public int getReaderId() {
	return readerId;
}
public long getIsbn() {
	return isbn;
}
public String getPickDate() {
	return pickDate;
}


}
