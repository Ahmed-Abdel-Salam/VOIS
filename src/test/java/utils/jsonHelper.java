package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class jsonHelper {
    //    public static <T> T[] getJsonData(String filePath, Class<T[]> clazz) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(new File(filePath), clazz);
//    }
    public static JsonNode getJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(filePath));
    }
}
