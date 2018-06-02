package telran.library.dto;

public class ReturnBookData {
int readerId;
long isbn;
String returnData;

public ReturnBookData() {
}

public ReturnBookData(int readerId, long isbn, String returnData) {
	this.readerId = readerId;
	this.isbn = isbn;
	this.returnData = returnData;
}

public int getReaderId() {
	return readerId;
}

public long getIsbn() {
	return isbn;
}

public String getReturnData() {
	return returnData;
}


}
