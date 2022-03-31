import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontEnd implements FrontEndInterface {
	private BackEndInterface backEndInterface;

	private int score = 0;

	@Override
	public void run(BackEndInterface searchEngine) {
		this.backEndInterface = searchEngine;
		menu();
	}

	// Here is a sample of the minimal set of options that
	// this front end will support:
	// MovieSearch Command Menu:
	// 1. Entering a rank to get information about the books
	// 2. Guess which book is higher rank.
	// 3. Entering in their own books with ranks that are above 100
	// 4. Quit
	// Get the whole menu of that movie tree
	@Override
	public void menu() {
		Scanner scnr = new Scanner(System.in);
		while (true) {
			System.out.println("Movie Tree Menu");
			System.out.println("1. Enter a Rank to Get Information About a Book");
			System.out.println("2. Guess Which Book is Higher Ranked?");
			System.out.println("3. Enter a Book of Your Own with Rank above 100");
			System.out.println("4. Quit");
			System.out.println("Please Select 1, 2, 3, or 4.");
			String result = scnr.next();
			
			if (result.equals("1")) {
				System.out.println("Please insert a rank number");
				int rank = scnr.nextInt();
				BookData book = new BookData(rank, "", "", "", "", 0, "", "");
				if (backEndInterface.containsBook(book)) {
					System.out.println("Book Title: " + backEndInterface.findTitles(rank) + "\nAuthor: "
							+ backEndInterface.findAuthor(rank) + "\nYear Published: "
							+ backEndInterface.findYearPublished(rank) + "\nPublishing Company: "
							+ backEndInterface.findPublisher(rank) + "\nAmount Sold: "
							+ backEndInterface.findAmountSold(rank) + "\nPrice of Book: "
							+ backEndInterface.findPriceOfBook(rank) + "\nGenre: " + backEndInterface.findGenre(rank));

				} else {
					System.out.print("Rank does not exist. Try again.");
					break;
				}
			} else if (result.equals("2")) {

			} else if (result.equals("3")) {
				// Insert an own movie
				System.out.println("Please insert a rank number");
				int rank = 101;
				boolean rank_bo = false;
				while (!rank_bo) {
					try {
						rank = scnr.nextInt();

						BookData rank_movie = new BookData(rank, "", "", "", "", 0.0, "", "");
						if (backEndInterface.containsBook(rank_movie)) {
							System.out.println("Sorry, that rank is already used, please try another one");
							continue;
						}
						rank_bo = true;
					} catch (InputMismatchException e) {
						System.out.println("Noninteger value included in Rank. Please Start Over.");
						scnr.nextLine();
					}
				}
				System.out.println("Please insert Title");
				String title = scnr.next();
				// When insert 2
				scnr.nextLine();
				System.out.println("Please insert Author Name");
				String author = scnr.nextLine();

				System.out.println("Please insert Publishing Company");
				String publisher = scnr.nextLine();

				System.out.println("Please insert Amount of Books Sold");
				String volume = scnr.nextLine();

				System.out.println("Please Insert Cost of Book");
				int ASPIndex = 0;
				boolean indexBool = false;
				while (!indexBool) {
					try {
						ASPIndex = scnr.nextInt();
						indexBool = true;
					} catch (InputMismatchException e) {
						System.out.println("Noninteger value included in Book Cost. Please Start Over.");
						scnr.nextLine();
					}
				}
				System.out.println("Please insert Year Published");
				String publishYear = scnr.nextLine();
				System.out.println("Please insert Book Genre");
				String genre = scnr.nextLine();
				BookData book = new BookData(rank, title, author, publisher, volume, ASPIndex, publishYear, genre);
				if (backEndInterface.containsBook(book)) {
					System.out.println("Sorry, that book already exist, please try another");
					continue;
				} else {
					backEndInterface.insert(book);
					System.out.println("Book inserted successfully");
				}
				continue;
			} else if (result.equals("4")) {
				break;
			} else {
				System.out.println("The input is invalid, please insert 1, 2, 3, or 4");
				continue;
			}
		}
	}

	/**
	 * return the current score
	 */
	public int score() {
		return this.score;
	}

	/**
	 * 
	 * @param str    the user input string
	 * @param target the target string
	 * @return true, if the input string contains target string, false else.
	 */
	public boolean stringCompare(String str, String target) {
		String[] str2 = str.split("\\s+|;|,");
		String[] target2 = target.split("\\s+|;|,");
		for (int i = 0; i < str2.length; i++) {
			for (int j = 0; j < target2.length; j++) {
				if (str2[i].equalsIgnoreCase(target2[j]))
					return true;
				System.out.println(str2[i] + " $$ " + target2[j]);
			}
		}
		return false;
	}

}
