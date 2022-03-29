import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BookLoad implements BookLoadInterface {
	public List<String> readCSVLine(String line) {
		List<String> output= new LinkedList<String>();
		int quoteCount = 0;
		String currentString = "";
		int charCount = 0;
		// add a comma to the end so it will properly capture the last string
		if (line.charAt(line.length() - 1) != ',') {
			line = line + ",";
		}
		for (char currentChar : line.toCharArray()) {
			charCount++;
			// end a block only when quotes are balanced
			if ((currentChar == ',' && quoteCount % 2 == 0)) {
				// This deals with empty strings
				if (currentString.equals("")) {
					output.add(currentString);
					continue;
				}
				// Get rid of the quote characters on either side of a block(if they exist)
				if (currentString.charAt(0) == '"' && currentString.charAt(currentString.length() - 1) == '"') {
					// this deals with strings that are just two quote characters
					if (currentString.length() == 2) {
						currentString = "";
						output.add(currentString);
						continue;
					}
					currentString = currentString.substring(1, currentString.length() - 1);
				}
				// turn the double quotes into single quotes
				currentString = currentString.replaceAll("\"\"", "\"");
				output.add(currentString);
				currentString = "";
				continue;
			}
			if (currentChar == '"') {
				quoteCount++;
			}
			currentString = currentString + currentChar;
		}
		return output;
	}

	
	@Override
	public List<BookDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
		Scanner scnr = new Scanner(new File(csvFilePath), "UTF-8");
		List<BookDataInterface> out = new LinkedList<BookDataInterface>();
		// Reads the header line of the CSV file and sets the index of the variable
		// names.
		
		List<String> header = readCSVLine(scnr.nextLine());
		int positionIndex = header.indexOf("Position");
		int titleIndex = header.indexOf("Title");
		int authorIndex = header.indexOf("Author");
		int imprintIndex = header.indexOf("Imprint");
		int volumeIndex = header.indexOf("Volume");
		int ASPIndex = header.indexOf("ASP");
		int publishIndex = header.indexOf("Publ Date");
		int genreIndex = header.indexOf("Product Class");
		while (scnr.hasNextLine()) {
			List<String> line = readCSVLine(scnr.nextLine());
			// extracts just the numerical part of the year(some had months)
			
			BookData temp = new BookData((Integer.parseInt(line.get(positionIndex))), line.get(titleIndex), line.get(authorIndex), line.get(imprintIndex), Integer.parseInt(line.get(volumeIndex)),
					Double.parseDouble(line.get(ASPIndex)), line.get(publishIndex), line.get(genreIndex));
			out.add(temp);
		}
		return out;

	}
}
