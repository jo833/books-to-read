

	public interface FrontEndInterface {
		 public void run(BackEndInterface searchEngine);
		    // Here is a sample of the minimal set of options that 
		    // this front end will support:
		    // MovieSearch Command Menu:
		    // 1. Entering a rank to get information about the books
		 	// 2. Guess which book is higher rank.
		    // 3. Entering in their own books with ranks that are above 100
		    // 4. Quit
		    void menu();
	}

