package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Path;
import java.util.Map;


import static java.nio.file.Files.readString; //Метод для чтения всех символов из файла в строку.

public class Parser {

    public static Map<String, Object> jsonToMap(Path file) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // readValue используется для преобразования (десериализации) JSON из строки, потока или файла в POJO.
        return objectMapper.readValue(readString(file), new TypeReference<Map<String, Object>>() {
        });
    }

    public static Map<String, Object> ymlToMap(Path file) throws Exception {
        ObjectMapper objectMapper = new YAMLMapper();
        return objectMapper.readValue(readString(file), new TypeReference<Map<String, Object>>() {
        });
    }
}
