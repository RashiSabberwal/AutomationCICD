package automationproject.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\automationproject\\data\\PurchaseOrder.json";
        
        // Use Path.of to create a Path object
        Path path = Path.of(filePath);
        
        // Read the file content as a string
        String content = Files.readString(path, StandardCharsets.UTF_8);
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String, String>> list = mapper.readValue(new File(filePath), List.class);
        return list;
        

		
	}

}
