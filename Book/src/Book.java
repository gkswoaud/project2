
import java.util.ArrayList;

public class Book {
	String title, writer, publisher, code, forrent;
	static ArrayList<Book> list = new ArrayList<Book>();
	
	Book (String title, String writer, String publisher, String code, String forrent)  {
		this.title = title;
		this.writer = writer;
		this.publisher = publisher;
		this.code = code;
		this.forrent = forrent;
	}
	
	void setForrent (String str)  {
		forrent = str;
	}

}
