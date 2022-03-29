import java.util.Iterator;

public interface BackEndInterface {

	int size();

	boolean containsBook(BookData book);

	String findTitles(int bookRank);
	
	int findAmountSold(int bookRank);

	double findPriceOfBook(int bookRank);

	String findAuthor(int bookRank);

	String findYearPublished(int bookRank);

	String findGenre(int bookRank);

	Iterator<BookData> iterator();

	boolean isEmpty();
	
	public boolean insert(BookDataInterface book);
	
}
