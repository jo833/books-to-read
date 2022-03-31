public class BookData implements BookDataInterface {

	private final int rank;
	private final String genre;
	private final String title;
	private final String author;
	private final double ASPIndex;
	private final String volume;
	private final String publishYear;
	private final String publisherImprint;

	public BookData(int rank, String title, String author, String publisherImprint, String volume, double ASPIndex,
			String publishYear, String genre) {
		this.rank = rank;
		this.title = title;
		this.author = author;
		this.ASPIndex = ASPIndex;
		this.volume = volume;
		this.publisherImprint = publisherImprint;
		this.publishYear = publishYear;
		this.genre = genre;

	}

	public int getRank() {

		return rank;
	}

	public String getTitle() {

		return title;
	}

	public String getGenre() {

		return genre;
	}

	public String getAuthor() {

		return author;
	}

	public String getAmountSold() {

		return volume;
	}

	public double getPriceOfBook() {

		return ASPIndex;
	}

	public String getPublisher() {

		return publisherImprint;
	}

	public String getYearPublished() {

		return publishYear;
	}

}

