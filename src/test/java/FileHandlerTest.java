import com.graphics.model.Polygon;
import com.graphics.util.files.FileHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FileHandlerTest {
	
	
	@Test
	public void readFile() {
		List<Polygon> polygons = FileHandler.readFile("src/test/resources/polygons.json");
		Assertions.assertEquals(1, polygons.size());
	}
}
