import java.util.List;
public class LoadScreen {
	
		  
		
		public static void main(String[] args) throws Exception {
	        System.out.println("Welcome to Best Books");
	        List<BookDataInterface> books = new BookLoad().loadFile("Top100booksALLTIME.csv");
	        BackEnd engine = new BackEnd();
	        for(BookDataInterface book : books) engine.insert(book);
	        FrontEndInterface ui = new FrontEnd();
	        ui.run(engine);
	    
	}

}
