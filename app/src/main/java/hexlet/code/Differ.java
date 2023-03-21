package hexlet.code;


import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


import static hexlet.code.Parser.fileToMap;

public class Differ {
    private static final String DEFAULT_FORMAT = "stylish";

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map = fileToMap(filePath1);
        Map<String, Object> map2 = fileToMap(filePath2);

        return switch (format) {
            case "json" -> Json.formatJson(map, map2);
            case "plain" -> Plain.formatPlain(map, map2);
            case "stylish" -> Stylish.formatStylish(map, map2);
            default -> "Format error";
        };
    }

    public static void generate(String filePath1, String filePath2) throws Exception {
        generate(filePath1, filePath2, DEFAULT_FORMAT);
    }

    public static Path pathFile(String string) throws Exception { // Формируем абсолютный путь
        Path path = Paths.get(string).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }
}
