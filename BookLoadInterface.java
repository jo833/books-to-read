import java.io.FileNotFoundException;
import java.util.List;

public interface BookLoadInterface {

	List<BookDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;

}
